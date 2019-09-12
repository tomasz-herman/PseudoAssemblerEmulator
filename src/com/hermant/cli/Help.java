package com.hermant.cli;

public class Help {

    public static void printUsage(){
        System.out.println("Usage:\njava --enable-preview -jar PseudoAssemblerEmulator.jar <options> ");
        System.out.println("Options:");
        System.out.println("--input=FILE\t-i FILE\tspecify input file(required)");
        System.out.println("--output=FILE\t-o FILE\tspecify a file that binary will be saved to(optional)");
        System.out.println("--sleep=number\t-s NUM\ttime in millis to sleep in between instructions(optional)");
        System.out.println("--debug\t\t\t-d\t\tenable debug(optional)");
        System.out.println("--unsafe\t\t-u\t\tdon't protect ram from leaking(optional)");
        System.out.println("--binary\t\t-b\t\tif input file is a binary(optional)");
        System.out.println("--abandon\t\t-a\t\tdon't run loaded program(optional)");
        System.out.println("--version\t\t-v\t\tdisplay version(optional)");
        System.out.println("--help\t\t\t-h\t\tdisplay this message(optional)");
    }

}
