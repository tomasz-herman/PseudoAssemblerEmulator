package com.hermant;

import com.hermant.machine.Machine;
import com.hermant.parser.Parser;
import com.hermant.program.Program;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        if(parseArgsForVersion(args))System.out.println("PseudoAssembler Emulator version 2.38.0 beta");
        if(parseArgsForHelp(args))usage();
        Program program;
        String input = parseArgsForInputFile(args);
        if(!parseArgsForBinary(args))
            program = Parser.parse(input);
        else
            program = deserializeBinary(input);
        String output = parseArgsForOutputFile(args);
        if(output != null)serializeProgram(program, output);
        if(parseArgsForAbandon(args))System.exit(0);
        Machine m = new Machine(parseArgsForDebug(args));
        m.loadProgram(program);
        int sleep = parseArgsForSleep(args);
        m.runProgram(sleep);
        m.free();
    }

    private static Program deserializeBinary(String path){
        Program program;
        try
        {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);

            program = (Program) ois.readObject();

            ois.close();
            fis.close();
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
            System.exit(1);
            program = new Program();
        }
        return program;
    }

    private static void serializeProgram(Program program, String path){
        try
        {
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(program);
            oos.close();
            fos.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static String parseArgsForInputFile(String[] args){
        for (String arg : args)
            if (arg.startsWith("--input="))
                return arg.substring(8);
        System.out.println("Specify input file:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private static String parseArgsForOutputFile(String[] args){
        for (String arg : args)
            if (arg.startsWith("--output="))
                return arg.substring(9);
        return null;
    }

    private static int parseArgsForSleep(String[] args){
        int sleep = 0;
        for (String arg : args)
            if (arg.startsWith("--sleep=")){
                sleep = Integer.parseInt(arg.substring(8));
                if(sleep < 0) sleep = 0;
                break;
            }
        return sleep;
    }

    private static boolean parseArgsForDebug(String[] args){
        for (String arg : args)
            if (arg.equals("--debug"))
                return true;
        return false;
    }

    private static boolean parseArgsForBinary(String[] args){
        for (String arg : args)
            if (arg.equals("--binary"))
                return true;
        return false;
    }

    private static boolean parseArgsForAbandon(String[] args){
        for (String arg : args)
            if (arg.equals("--abandon"))
                return true;
        return false;
    }

    private static boolean parseArgsForHelp(String[] args){
        for (String arg : args)
            if (arg.equals("--help"))
                return true;
        return false;
    }

    private static boolean parseArgsForVersion(String[] args){
        for (String arg : args)
            if (arg.equals("--version"))
                return true;
        return false;
    }

    private static void usage(){
        System.out.println("Usage:\njava --enable-preview -jar PseudoAssemblerEmulator.jar <flags>");
        System.out.println("Flags:");
        System.out.println("--input=FILE\tspecify input file(optional, will ask for it if not specified)");
        System.out.println("--output=FILE\tspecify a file that binary will be saved to(optional)");
        System.out.println("--sleep=number\tspecify a time in millis to sleep in between executing instructions(optional)");
        System.out.println("--debug\t\t\tenable debug(optional)");
        System.out.println("--binary\t\tif input file is a binary(optional)");
        System.out.println("--abandon\t\tdon't run loaded program(optional)");
        System.out.println("--version\t\tdisplay version(optional)");
        System.out.println("--help\t\t\tdisplay this message(optional)");
    }
}
