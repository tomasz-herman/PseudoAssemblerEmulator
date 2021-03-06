package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class CosineFloatInstruction extends Instruction {

    @Override
    public final void run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        m.getFPR().set(reg1, (float)Math.cos(m.getFPR().get(reg1)));
    }

    @Override
    public final byte code() {
        return COSINE_FLOAT;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "COSINE_FLOAT";
    }
}