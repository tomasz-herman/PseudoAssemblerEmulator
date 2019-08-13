package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class JumpLessOrEqualInstruction extends Instruction implements JumpOperation {

    JumpLessOrEqualInstruction(Byte reg1, Byte reg2, Short ramAddress) {
        super(Instruction.JUMP_LESS_OR_EQUAL, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        if(m.getFlagsRegister().isLessOrEqual()) jump(m.getInstructionPointer(), ramAddress);
        return true;
    }

    @Override
    public String instCode() {
        return "JUMP_LESS_OR_EQUAL";
    }
}