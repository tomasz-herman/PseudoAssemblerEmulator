package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class StoreFloatAsIntegerInstruction extends Instruction implements MemoryOperation {

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        m.getRam().setInteger(ramAddress, (int)m.getFPR().getFloat(reg1));
        return true;
    }

    @Override
    public byte code() {
        return STORE_FLOAT_AS_INTEGER;
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