package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class PushFlagsInstruction extends Instruction {

    PushFlagsInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.PUSH_FLAGS, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        m.getStack().push(m.getFlagsRegister().getFlags());
        return true;
    }
}
