package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class JumpEqualInstruction extends Instruction implements JumpOperation {

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        if(m.getFlagsRegister().isEqual()) jump(m.getInstructionPointer(), ramAddress);
        return true;
    }

    @Override
    public byte code() {
        return JUMP_EQUAL;
    }

    @Override
    public int instLength() {
        return 4;
    }

    @Override
    public String instCode() {
        return "JUMP_EQUAL";
    }
}