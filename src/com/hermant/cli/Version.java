package com.hermant.cli;

public class Version {

    private static final int MAJOR = 2;
    private static final int MINOR = 40;
    private static final int REVISION = 1;

    private static final String PROJECT_NAME = "PseudoAssembler Emulator";
    private static final String FULL = PROJECT_NAME + " " + MAJOR + "." + MINOR + "." + REVISION;

    public static void print(){
        System.out.println(FULL);
    }

}
