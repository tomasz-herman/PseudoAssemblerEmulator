package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class TestInstruction extends Instruction implements LogicalOperation, MemoryOperation {

    @Override
    public final void run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        final int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        logical(m.getRegister().get(reg1) & m.getRam().getInteger(ramAddress), m.getFlagsRegister());
    }

    @Override
    public final byte code() {
        return TEST;
    }

    @Override
    public final int instLength() {
        return 4;
    }

    @Override
    public final String instCode() {
        return "TEST";
    }
}