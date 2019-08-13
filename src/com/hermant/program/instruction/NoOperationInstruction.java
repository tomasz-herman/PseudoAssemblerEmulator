package com.hermant.program.instruction;

public class NoOperationInstruction extends Instruction {

    NoOperationInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(Instruction.NO_OPERATION, reg1, reg2, ramOffset);
    }

    @Override
    public String instCode() {
        return "NO_OPERATION";
    }
}