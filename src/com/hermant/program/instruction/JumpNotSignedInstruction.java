package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class JumpNotSignedInstruction extends Instruction implements JumpOperation {

    JumpNotSignedInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(Instruction.JUMP_NOT_SIGNED, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        if(m.getFlagsRegister().isNotSigned()) jump(m.getInstructionPointer(), ramAddress);
        return true;
    }

    @Override
    public String instCode() {
        return "JUMP_NOT_SIGNED";
    }
}