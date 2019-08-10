package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class CompareFloatInstruction extends Instruction {

    CompareFloatInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.COMPARE_FLOAT, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister());
        compareFloat(m.getFPR().getFloat(reg1), m.getRam().getFloat(ramAddress), m.getFlagsRegister());
        return true;
    }

    @Override
    public String instCode() {
        return "COMPARE_FLOAT";
    }
}