package com.hermant.program.instruction;

import com.hermant.machine.*;

public class LoadRegisterInstruction extends Instruction {

    @Override
    public final boolean execute(Machine m, boolean debug){
        if(debug) debug(m.getInstructionPointer());
        setInstructionPointer(m.getInstructionPointer());
        m.getRegister().setInteger(reg1, m.getRegister().getInteger(reg2));
        return true;
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