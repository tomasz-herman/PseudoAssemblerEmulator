package com.hermant;

import com.hermant.machine.Machine;
import com.hermant.parser.Parser;
import com.hermant.program.Program;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("PseudoAssembler Emulator version 2.22.0 beta");
        usage();
        Program program = Parser.parse(parseArgsForInputFile(args));
        Machine m = new Machine(parseArgsForDebug(args));
        m.loadProgram(program);
        m.runProgram();
    }

    private static String parseArgsForInputFile(String[] args){
        for (String arg : args)
            if (arg.startsWith("--input="))
                return arg.substring(8);
        System.out.println("Specify input file:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private static boolean parseArgsForDebug(String[] args){
        for (String arg : args)
            if (arg.equals("--debug"))
                return true;
        return false;
    }

    private static void usage(){
        System.out.println("Usage:\njava --enable-preview -jar PseudoAssemblerEmulator.jar <flags>");
        System.out.println("Flags:");
        System.out.println("--input=FILE\tspecify input file(optional, will ask for it if not specified)");
        System.out.println("--debug\t\t\tenable debug(optional)");
    }
}
