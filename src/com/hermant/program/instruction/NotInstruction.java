package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class NotInstruction extends Instruction implements MemoryOperation {

    @Override
    public final boolean run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        final int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        m.getRam().setInteger(ramAddress, ~m.getRam().getInteger(ramAddress));
        return true;
    }

    @Override
    public final byte code() {
        return NOT;
    }

    @Override
    public final int instLength() {
        return 4;
    }

    @Override
    public final String instCode() {
        return "NOT";
    }
}