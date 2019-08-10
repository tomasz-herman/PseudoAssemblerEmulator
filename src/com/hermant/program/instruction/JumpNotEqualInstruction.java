package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class JumpNotEqualInstruction extends Instruction implements JumpOperation {

    JumpNotEqualInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.JUMP_NOT_EQUAL, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister());
        if(m.getFlagsRegister().isNotEqual()) jump(m.getRegister(), ramAddress);
        return true;
    }

    @Override
    public String instCode() {
        return "JUMP_NOT_EQUAL";
    }
}