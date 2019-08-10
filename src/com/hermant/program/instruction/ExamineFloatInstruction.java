package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class ExamineFloatInstruction extends Instruction {

    ExamineFloatInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.EXAMINE_FLOAT, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister());
        examineFloat(m.getRam().getFloat(ramAddress), m.getFlagsRegister());
        return true;
    }

    @Override
    public String instCode() {
        return "EXAMINE_FLOAT";
    }
}