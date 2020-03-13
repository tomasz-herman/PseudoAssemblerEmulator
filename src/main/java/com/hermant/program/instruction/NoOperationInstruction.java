package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class NoOperationInstruction extends Instruction {

    @Override
    public final void run(Machine m) { }

    @Override
    public final byte code() {
        return NO_OPERATION;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "NO_OPERATION";
    }
}