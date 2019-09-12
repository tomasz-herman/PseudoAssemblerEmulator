package com.hermant;

import com.hermant.cli.*;
import com.hermant.machine.Machine;
import com.hermant.parser.Parser;
import com.hermant.program.Program;
import com.hermant.serializer.Serializer;

public class Main {

    public static void main(String[] args) {
        Options options = ArgsParser.parse(args);
        if(options.version())Version.print();
        if(options.help()) Help.printUsage();
        Program program = options.binary() ?
                Serializer.deserializeBinary(options.input()) : Parser.parse(options.input());
        if(!options.output().isEmpty()) Serializer.serializeProgram(program, options.output());
        if(options.abandon())System.exit(0);
        Machine m = new Machine(options.debug(), options.unsafe());
        m.loadProgram(program);
        m.runProgram(options.sleep());
        m.free();
    }
}
