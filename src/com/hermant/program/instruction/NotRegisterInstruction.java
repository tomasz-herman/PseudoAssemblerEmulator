package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class NotRegisterInstruction extends Instruction {

    NotRegisterInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(NOT_REGISTER, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        m.getRegister().setInteger(reg1, ~m.getRegister().getInteger(reg1));
        return true;
    }

    @Override
    public int instLength() {
        return 2;
    }

    @Override
    public String instCode() {
        return "NOT_REGISTER";
    }
}