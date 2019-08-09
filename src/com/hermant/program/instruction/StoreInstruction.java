package com.hermant.program.instruction;

import com.hermant.machine.*;

public class StoreInstruction extends Instruction {

    StoreInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.STORE, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister());
        m.getRam().setInteger(ramAddress, m.getRegister().getInteger(reg1));
        return true;
    }
}
