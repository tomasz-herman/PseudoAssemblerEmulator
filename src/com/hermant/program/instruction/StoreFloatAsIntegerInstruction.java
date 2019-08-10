package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class StoreFloatAsIntegerInstruction extends Instruction {

    StoreFloatAsIntegerInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.STORE_FLOAT_AS_INTEGER, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister());
        m.getRam().setInteger(ramAddress, (int)m.getFPR().getFloat(reg1));
        return true;
    }

    @Override
    public String instCode() {
        return "STORE_FLOAT_AS_INTEGER";
    }
}