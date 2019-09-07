package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class NotRegisterInstruction extends Instruction {

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        m.getRegister().setInteger(reg1, ~m.getRegister().getInteger(reg1));
        return true;
    }

    @Override
    public byte code() {
        return NOT_REGISTER;
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