package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class AddInstruction extends Instruction {

    AddInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.ADD, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister());
        int a = m.getRegister().getInteger(reg1);
        int b = m.getRam().getInteger(ramAddress);
        compare(a, b, m.getFlagsRegister(), Long::sum);
        m.getRegister().setInteger(reg1, a + b);
        return true;
    }
}