package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class SineFloatInstruction extends Instruction {

    @Override
    public final void run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        m.getFPR().set(reg1, (float)Math.sin(m.getFPR().get(reg1)));
    }

    @Override
    public final byte code() {
        return SINE_FLOAT;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "SINE_FLOAT";
    }
}