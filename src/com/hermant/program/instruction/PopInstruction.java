package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class PopInstruction extends Instruction implements MemoryOperation {

    @Override
    public final void run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        final int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        m.getRam().setInteger(ramAddress, m.getStack().pop());
    }

    @Override
    public final byte code() {
        return POP;
    }

    @Override
    public final int instLength() {
        return 4;
    }

    @Override
    public final String instCode() {
        return "POP";
    }
}
