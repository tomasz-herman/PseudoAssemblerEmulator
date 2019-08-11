package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class RandomRegisterInstruction extends Instruction {

    RandomRegisterInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.RANDOM_REGISTER, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        m.getRegister().setInteger(reg1, m.getRng().getNext());
        return true;
    }

    @Override
    public String instCode() {
        return "RANDOM_REGISTER";
    }
}