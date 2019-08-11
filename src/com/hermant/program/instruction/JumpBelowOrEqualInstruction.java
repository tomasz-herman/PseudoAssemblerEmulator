package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class JumpBelowOrEqualInstruction extends Instruction implements JumpOperation {

    JumpBelowOrEqualInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.JUMP_BELOW_OR_EQUAL, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister());
        if(m.getFlagsRegister().isBelowOrEqual()) jump(m.getInstructionPointer(), ramAddress);
        return true;
    }

    @Override
    public String instCode() {
        return "JUMP_BELOW_OR_EQUAL";
    }
}