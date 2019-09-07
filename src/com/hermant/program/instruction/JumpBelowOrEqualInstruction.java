package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class JumpBelowOrEqualInstruction extends Instruction implements JumpOperation {

    @Override
    public final boolean execute(Machine m, boolean debug){
        if(debug) debug(m.getInstructionPointer());
        final int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        if(m.getFlagsRegister().isBelowOrEqual()) jump(m.getInstructionPointer(), ramAddress);
        else setInstructionPointer(m.getInstructionPointer());
        return true;
    }

    @Override
    public final byte code() {
        return JUMP_BELOW_OR_EQUAL;
    }

    @Override
    public final int instLength() {
        return 4;
    }

    @Override
    public final String instCode() {
        return "JUMP_BELOW_OR_EQUAL";
    }
}