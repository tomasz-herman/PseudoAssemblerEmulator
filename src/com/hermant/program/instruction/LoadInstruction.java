package com.hermant.program.instruction;

import com.hermant.machine.*;

public class LoadInstruction extends Instruction {

    LoadInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.LOAD, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister());
        m.getRegister().setInteger(reg1, m.getRam().getInteger(ramAddress));
        return true;
    }
}
