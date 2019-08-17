package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class PopFlagsInstruction extends Instruction {

    PopFlagsInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(Instruction.POP_FLAGS, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        m.getFlagsRegister().setFlags(m.getStack().pop());
        return true;
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
