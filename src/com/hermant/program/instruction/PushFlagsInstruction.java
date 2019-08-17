package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class PushFlagsInstruction extends Instruction {

    PushFlagsInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(Instruction.PUSH_FLAGS, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        m.getStack().push(m.getFlagsRegister().getFlags());
        return true;
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
