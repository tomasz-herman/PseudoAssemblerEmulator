package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class JumpGreaterInstruction extends Instruction implements JumpOperation {

    JumpGreaterInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(Instruction.JUMP_GREATER, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        if(m.getFlagsRegister().isGreater()) jump(m.getInstructionPointer(), ramAddress);
        return true;
    }

    @Override
    public int instLength() {
        return 4;
    }

    @Override
    public String instCode() {
        return "JUMP_GREATER";
    }
}