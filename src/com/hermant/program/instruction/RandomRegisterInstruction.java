package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class RandomRegisterInstruction extends Instruction {

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        m.getRegister().setInteger(reg1, m.getRng().getNext());
        return true;
    }

    @Override
    public byte code() {
        return RANDOM_REGISTER;
    }

    @Override
    public int instLength() {
        return 2;
    }

    @Override
    public String instCode() {
        return "RANDOM_REGISTER";
    }
}