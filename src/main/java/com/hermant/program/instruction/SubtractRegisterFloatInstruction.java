package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class SubtractRegisterFloatInstruction extends Instruction {

    @Override
    public final void run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        m.getFPR().set(reg1, m.getFPR().get(reg1) - m.getFPR().get(reg2));
    }

    @Override
    public final byte code() {
        return SUBTRACT_REGISTER_FLOAT;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "SUBTRACT_REGISTER_FLOAT";
    }
}