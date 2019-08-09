package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class PopFlagsInstruction extends Instruction {

    PopFlagsInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.POP_FLAGS, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        m.getFlagsRegister().setFlags(m.getStack().pop());
        return true;
    }
}
