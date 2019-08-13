package com.hermant.program.instruction;

import com.hermant.machine.*;

public class LoadRegisterInstruction extends Instruction {

    LoadRegisterInstruction(Byte reg1, Byte reg2, Short ramAddress) {
        super(Instruction.LOAD_REGISTER, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        m.getRegister().setInteger(reg1, m.getRegister().getInteger(reg2));
        return true;
    }

    @Override
    public String instCode() {
        return "LOAD_REGISTER";
    }
}