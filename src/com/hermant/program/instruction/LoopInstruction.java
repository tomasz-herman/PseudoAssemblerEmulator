package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class LoopInstruction extends Instruction implements JumpOperation {

    LoopInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.LOOP, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister());
        m.getRegister().setInteger(reg1, m.getRegister().getInteger(reg1) - 1);
        if(m.getRegister().getInteger(reg1)!=0) jump(m.getInstructionPointer(), ramAddress);
        return true;
    }

    @Override
    public String instCode() {
        return "LOOP";
    }
}