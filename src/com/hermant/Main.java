package com.hermant;

import com.hermant.machine.Machine;
import com.hermant.parser.Parser;
import com.hermant.program.Program;

public class Main {

    public static void main(String[] args) {
        System.out.println("PseudoAssembler Emulator version 2.19.11 beta");
        usage();
        Program program = Parser.parse(parseArgsForInputFile(args));
        Machine m = new Machine(parseArgsForDebug(args));
        m.loadProgram(program);
        m.runProgram();
    }

    private static String parseArgsForInputFile(String[] args){
        for (int i = 0; i < args.length; i++) {
            if(args[i].equals("-i")||args[i].equals("--input"))
                if(i<args.length-1)
                    return args[i+1];
                else{
                    throw new IllegalStateException("no input file specified");
                }
        }
        throw new IllegalStateException("Missing required option: -i");
    }

    private static boolean parseArgsForDebug(String[] args){
        for (String arg : args)
            if (arg.equals("-d") || arg.equals("--debug"))
                return true;
        return false;
    }

    private static void usage(){
        System.out.println("Usage:\njava -jar utility-name -i <input file> [--debug]");
    }
}
