package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class AddRegisterInstruction extends Instruction {

    AddRegisterInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.ADD_REGISTER, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int a = m.getRegister().getInteger(reg1);
        int b = m.getRegister().getInteger(reg2);
        compare(a, b, m.getFlagsRegister(), Long::sum);
        m.getRegister().setInteger(reg1, a + b);
        return true;
    }
}