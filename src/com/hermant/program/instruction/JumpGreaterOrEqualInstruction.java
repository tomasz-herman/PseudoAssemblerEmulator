package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class JumpGreaterOrEqualInstruction extends Instruction {

    JumpGreaterOrEqualInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.JUMP_GREATER_OR_EQUAL, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister());
        if(m.getFlagsRegister().isGreaterOrEqual()) jump(m.getRegister(), ramAddress);
        return true;
    }
}