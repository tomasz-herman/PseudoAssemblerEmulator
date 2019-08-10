package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class StoreFloatInstruction extends Instruction {

    StoreFloatInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.STORE_FLOAT, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister());
        m.getRam().setInteger(ramAddress, m.getFPR().getInteger(reg1));
        return true;
    }

    @Override
    public String instCode() {
        return "STORE_FLOAT";
    }
}
