package com.hermant.program.instruction;

import com.hermant.machine.*;

public class StoreInstruction extends Instruction implements MemoryOperation {

    @Override
    public final boolean run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        final int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        m.getRam().setInteger(ramAddress, m.getRegister().getInteger(reg1));
        return true;
    }

    @Override
    public final byte code() {
        return STORE;
    }

    @Override
    public final int instLength() {
        return 4;
    }

    @Override
    public final String instCode() {
        return "STORE";
    }
}
