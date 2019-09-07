package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class JumpNotParityInstruction extends Instruction implements JumpOperation {

    @Override
    public final boolean run(Machine m){
        final int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        if(m.getFlagsRegister().isParityOdd()) jump(m.getInstructionPointer(), ramAddress);
        else setInstructionPointer(m.getInstructionPointer());
        return true;
    }

    @Override
    public final byte code() {
        return JUMP_NOT_PARITY;
    }

    @Override
    public final int instLength() {
        return 4;
    }

    @Override
    public final String instCode() {
        return "JUMP_NOT_PARITY";
    }
}