package com.hermant.cli;

public class Version {

    private static final int MAJOR = 3;
    private static final int MINOR = 1;
    private static final int REVISION = 0;

    private static final String PROJECT_NAME = "PseudoAssembler Emulator";
    private static final String FULL = PROJECT_NAME + " " + MAJOR + "." + MINOR + "." + REVISION;

    public static void print(){
        System.out.println(FULL);
    }
    public static String getFull(){
        return FULL;
    }

}
