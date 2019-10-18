package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class JumpLessOrEqualInstruction extends Instruction implements JumpOperation, MemoryOperation {

    @Override
    public final void run(Machine m){
        final int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        if(m.getFlagsRegister().isLessOrEqual()) jump(m.getInstructionPointer(), ramAddress);
        else setInstructionPointer(m.getInstructionPointer());
    }

    @Override
    public final byte code() {
        return JUMP_LESS_OR_EQUAL;
    }

    @Override
    public final int instLength() {
        return 4;
    }

    @Override
    public final String instCode() {
        return "JUMP_LESS_OR_EQUAL";
    }
}