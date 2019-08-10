package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class JumpNotSignedInstruction extends Instruction implements JumpOperation {

    JumpNotSignedInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.JUMP_NOT_SIGNED, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister());
        if(m.getFlagsRegister().isNotSigned()) jump(m.getRegister(), ramAddress);
        return true;
    }

    @Override
    public String instCode() {
        return "JUMP_NOT_SIGNED";
    }
}