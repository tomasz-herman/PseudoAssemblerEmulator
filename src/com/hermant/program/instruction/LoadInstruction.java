package com.hermant.program.instruction;

import com.hermant.machine.*;

public class LoadInstruction extends Instruction implements MemoryOperation {

    @Override
    public final void run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        final int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        m.getRegister().set(reg1, m.getRam().getInteger(ramAddress));
    }

    @Override
    public final byte code() {
        return LOAD;
    }

    @Override
    public final int instLength() {
        return 4;
    }

    @Override
    public final String instCode() {
        return "LOAD";
    }
}
