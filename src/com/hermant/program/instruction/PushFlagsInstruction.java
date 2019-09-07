package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class PushFlagsInstruction extends Instruction {

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        m.getStack().push(m.getFlagsRegister().getFlags());
        return true;
    }

    @Override
    public byte code() {
        return PUSH_FLAGS;
    }

    @Override
    public int instLength() {
        return 2;
    }

    @Override
    public String instCode() {
        return "PUSH_FLAGS";
    }
}
