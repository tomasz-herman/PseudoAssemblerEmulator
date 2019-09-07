package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class PushFlagsInstruction extends Instruction {

    @Override
    public final boolean run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        m.getStack().push(m.getFlagsRegister().getFlags());
        return true;
    }

    @Override
    public final byte code() {
        return PUSH_FLAGS;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "PUSH_FLAGS";
    }
}
