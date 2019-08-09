package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class CompareRegisterInstruction extends Instruction {

    CompareRegisterInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.COMPARE_REGISTER, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int a = m.getRegister().getInteger(reg1);
        int b = m.getRegister().getInteger(reg2);
        compare(a, b, m.getFlagsRegister(), (x, y) -> x - y);
        return true;
    }
}