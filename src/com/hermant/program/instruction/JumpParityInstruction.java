package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class JumpParityInstruction extends Instruction implements JumpOperation {

    @Override
    public final boolean execute(Machine m, boolean debug){
        if(debug) debug(m.getInstructionPointer());
        final int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        if(m.getFlagsRegister().isParityEven()) jump(m.getInstructionPointer(), ramAddress);
        else setInstructionPointer(m.getInstructionPointer());
        return true;
    }

    @Override
    public final byte code() {
        return JUMP_PARITY;
    }

    @Override
    public final int instLength() {
        return 4;
    }

    @Override
    public final String instCode() {
        return "JUMP_PARITY";
    }
}