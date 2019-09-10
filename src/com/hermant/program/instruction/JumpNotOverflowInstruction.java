package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class JumpNotOverflowInstruction extends Instruction implements JumpOperation {

    @Override
    public final void run(Machine m){
        final int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        if(m.getFlagsRegister().isNotOverflow()) jump(m.getInstructionPointer(), ramAddress);
        else setInstructionPointer(m.getInstructionPointer());
    }

    @Override
    public final byte code() {
        return JUMP_NOT_OVERFLOW;
    }

    @Override
    public final int instLength() {
        return 4;
    }

    @Override
    public final String instCode() {
        return "JUMP_NOT_OVERFLOW";
    }
}