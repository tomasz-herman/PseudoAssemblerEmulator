package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class JumpSignedInstruction extends Instruction implements JumpOperation {

    JumpSignedInstruction(Byte reg1, Byte reg2, Short ramAddress) {
        super(Instruction.JUMP_SIGNED, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        if(m.getFlagsRegister().isSigned()) jump(m.getInstructionPointer(), ramAddress);
        return true;
    }

    @Override
    public String instCode() {
        return "JUMP_SIGNED";
    }
}