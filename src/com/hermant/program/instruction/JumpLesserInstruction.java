package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class JumpLesserInstruction extends Instruction implements JumpOperation {

    JumpLesserInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.JUMP_LESSER, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister());
        if(m.getFlagsRegister().isLesser()) jump(m.getRegister(), ramAddress);
        return true;
    }

    @Override
    public String instCode() {
        return "JUMP_LESSER";
    }
}