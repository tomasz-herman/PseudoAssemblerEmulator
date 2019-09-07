package com.hermant.program.instruction;

import com.hermant.machine.*;

public class LoadRegisterFloatInstruction extends Instruction {

    @Override
    public final boolean execute(Machine m, boolean debug){
        if(debug) debug(m.getInstructionPointer());
        setInstructionPointer(m.getInstructionPointer());
        m.getFPR().setInteger(reg1, m.getFPR().getInteger(reg2));
        return true;
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