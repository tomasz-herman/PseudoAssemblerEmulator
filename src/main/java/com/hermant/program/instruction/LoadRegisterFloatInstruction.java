package com.hermant.program.instruction;

import com.hermant.machine.*;

public class LoadRegisterFloatInstruction extends Instruction {

    @Override
    public final void run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        m.getFPR().set(reg1, m.getFPR().get(reg2));
    }

    @Override
    public final byte code() {
        return LOAD_REGISTER_FLOAT;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "LOAD_REGISTER_FLOAT";
    }
}