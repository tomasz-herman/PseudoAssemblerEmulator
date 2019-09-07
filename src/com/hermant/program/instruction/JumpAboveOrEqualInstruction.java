package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class JumpAboveOrEqualInstruction extends Instruction implements JumpOperation {

    @Override
    public final boolean run(Machine m){
        final int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        if(m.getFlagsRegister().isAboveOrEqual()) jump(m.getInstructionPointer(), ramAddress);
        else setInstructionPointer(m.getInstructionPointer());
        return true;
    }

    @Override
    public final byte code() {
        return JUMP_ABOVE_OR_EQUAL;
    }

    @Override
    public final int instLength() {
        return 4;
    }

    @Override
    public final String instCode() {
        return "JUMP_ABOVE_OR_EQUAL";
    }
}