package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class StoreFloatAsIntegerInstruction extends Instruction implements MemoryOperation {

    StoreFloatAsIntegerInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(Instruction.STORE_FLOAT_AS_INTEGER, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        m.getRam().setInteger(ramAddress, (int)m.getFPR().getFloat(reg1));
        return true;
    }

    @Override
    public int instLength() {
        return 4;
    }

    @Override
    public String instCode() {
        return "STORE_FLOAT_AS_INTEGER";
    }
}