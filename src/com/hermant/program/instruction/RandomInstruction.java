package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class RandomInstruction extends Instruction implements MemoryOperation {

    @Override
    public final void run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        final int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        m.getRam().setInteger(ramAddress, m.getRng().getNext());
    }

    @Override
    public final byte code() {
        return RANDOM;
    }

    @Override
    public final int instLength() {
        return 4;
    }

    @Override
    public final String instCode() {
        return "RANDOM";
    }
}