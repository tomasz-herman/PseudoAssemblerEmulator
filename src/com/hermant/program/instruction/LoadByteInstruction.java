package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class LoadByteInstruction extends Instruction implements MemoryOperation {

    @Override
    public final boolean run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        final int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        m.getRegister().set(reg1, m.getRam().getByte(ramAddress));
        return true;
    }

    @Override
    public final byte code() {
        return LOAD_BYTE;
    }

    @Override
    public final int instLength() {
        return 4;
    }

    @Override
    public final String instCode() {
        return "LOAD_BYTE";
    }
}