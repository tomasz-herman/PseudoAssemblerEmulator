package com.hermant.program.instruction;

import com.hermant.machine.*;

public class LoadRegisterFloatInstruction extends Instruction {

    LoadRegisterFloatInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(LOAD_REGISTER_FLOAT, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        m.getFPR().setInteger(reg1, m.getFPR().getInteger(reg2));
        return true;
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