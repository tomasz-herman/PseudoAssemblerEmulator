package com.hermant.program.instruction;

import com.hermant.machine.*;

public class LoadFloatInstruction extends Instruction implements MemoryOperation {

    @Override
    public final void run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        final int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        m.getFPR().set(reg1, m.getRam().getFloat(ramAddress));
    }

    @Override
    public final byte code() {
        return LOAD_FLOAT;
    }

    @Override
    public final int instLength() {
        return 4;
    }

    @Override
    public final String instCode() {
        return "LOAD_FLOAT";
    }
}
