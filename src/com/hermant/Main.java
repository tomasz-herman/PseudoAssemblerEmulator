package com.hermant;

import com.hermant.cli.*;
import com.hermant.gui.Window;
import com.hermant.machine.Machine;
import com.hermant.parser.ParseException;
import com.hermant.parser.Parser;
import com.hermant.program.Program;
import com.hermant.serializer.SerializationException;
import com.hermant.serializer.Serializer;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        if(args.length == 0) { new Window(); }
        else {
            Options options = ArgsParser.parse(args);
            if(options.version())Version.print();
            if(options.help()) Help.printUsage();
            Program program = null;
            try {
                program = options.binary() ?
                        Serializer.deserializeBinary(options.input()) : Parser.parse(options.input());
            } catch (IOException | SerializationException | ParseException e) {
                e.printStackTrace();
                System.exit(-1);
            }
            if(!options.output().isEmpty()) {
                try {
                    Serializer.serializeProgram(program, options.output());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(options.abandon())System.exit(0);
            Machine m = new Machine(options.debug(), options.unsafe());
            m.loadProgram(program);
            m.runProgram(options.sleep());
            m.free();
        }
    }
}
