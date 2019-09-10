package com.hermant.program.instruction;

import com.hermant.machine.*;

public class LoadRegisterInstruction extends Instruction {

    @Override
    public final void run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        m.getRegister().set(reg1, m.getRegister().get(reg2));
    }

    @Override
    public final byte code() {
        return LOAD_REGISTER;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "LOAD_REGISTER";
    }
}