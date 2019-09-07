package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class PopFlagsInstruction extends Instruction {

    @Override
    public final boolean run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        m.getFlagsRegister().setFlags(m.getStack().pop());
        return true;
    }

    @Override
    public final byte code() {
        return POP_FLAGS;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "POP_FLAGS";
    }
}
