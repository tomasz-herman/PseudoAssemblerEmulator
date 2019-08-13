package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class SubtractRegisterInstruction extends Instruction {

    SubtractRegisterInstruction(Byte reg1, Byte reg2, Short ramAddress) {
        super(Instruction.SUBTRACT_REGISTER, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int a = m.getRegister().getInteger(reg1);
        int b = m.getRegister().getInteger(reg2);
        compare(a, b, m.getFlagsRegister(), (x, y) -> x - y);
        m.getRegister().setInteger(reg1, a - b);
        return true;
    }

    @Override
    public String instCode() {
        return "SUBTRACT_REGISTER";
    }
}