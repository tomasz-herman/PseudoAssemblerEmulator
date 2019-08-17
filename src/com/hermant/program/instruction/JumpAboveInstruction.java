package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class JumpAboveInstruction extends Instruction implements JumpOperation {

    JumpAboveInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(Instruction.JUMP_ABOVE, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        if(m.getFlagsRegister().isAbove()) jump(m.getInstructionPointer(), ramAddress);
        return true;
    }

    @Override
    public int instLength() {
        return 4;
    }

    @Override
    public String instCode() {
        return "JUMP_ABOVE";
    }
}