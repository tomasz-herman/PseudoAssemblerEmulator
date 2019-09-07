package com.hermant.program.instruction;

public class NoOperationInstruction extends Instruction {

    @Override
    public byte code() {
        return NO_OPERATION;
    }

    @Override
    public int instLength() {
        return 2;
    }

    @Override
    public String instCode() {
        return "NO_OPERATION";
    }
}