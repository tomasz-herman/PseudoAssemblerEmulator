package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class PopFlagsInstruction extends Instruction {

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        m.getFlagsRegister().setFlags(m.getStack().pop());
        return true;
    }

    @Override
    public byte code() {
        return POP_FLAGS;
    }

    @Override
    public int instLength() {
        return 2;
    }

    @Override
    public String instCode() {
        return "POP_FLAGS";
    }
}
