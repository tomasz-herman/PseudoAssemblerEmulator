package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class TangentFloatInstruction extends Instruction {

    @Override
    public final void run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        m.getFPR().set(reg1, (float)Math.tan(m.getFPR().get(reg1)));
    }

    @Override
    public final byte code() {
        return TANGENT_FLOAT;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "TANGENT_FLOAT";
    }
}