package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class NotRegisterInstruction extends Instruction {

    NotRegisterInstruction(Byte reg1, Byte reg2, Short ramAddress) {
        super(Instruction.NOT_REGISTER, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        m.getRegister().setInteger(reg1, ~m.getRegister().getInteger(reg1));
        return true;
    }

    @Override
    public String instCode() {
        return "NOT_REGISTER";
    }
}