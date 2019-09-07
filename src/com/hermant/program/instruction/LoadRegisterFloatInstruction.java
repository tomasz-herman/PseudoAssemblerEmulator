package com.hermant.program.instruction;

import com.hermant.machine.*;

public class LoadRegisterFloatInstruction extends Instruction {

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        m.getFPR().setInteger(reg1, m.getFPR().getInteger(reg2));
        return true;
    }

    @Override
    public byte code() {
        return LOAD_REGISTER_FLOAT;
    }

    @Override
    public int instLength() {
        return 2;
    }

    @Override
    public String instCode() {
        return "LOAD_REGISTER_FLOAT";
    }
}