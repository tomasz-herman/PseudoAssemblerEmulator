package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class StoreFloatAsIntegerInstruction extends Instruction implements MemoryOperation {

    @Override
    public final boolean execute(Machine m, boolean debug){
        if(debug) debug(m.getInstructionPointer());
        setInstructionPointer(m.getInstructionPointer());
        final int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        m.getRam().setInteger(ramAddress, (int)m.getFPR().getFloat(reg1));
        return true;
    }

    @Override
    public final byte code() {
        return STORE_FLOAT_AS_INTEGER;
    }

    @Override
    public final int instLength() {
        return 4;
    }

    @Override
    public final String instCode() {
        return "STORE_FLOAT_AS_INTEGER";
    }
}