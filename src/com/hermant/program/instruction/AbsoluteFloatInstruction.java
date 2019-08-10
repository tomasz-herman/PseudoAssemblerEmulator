package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class AbsoluteFloatInstruction extends Instruction {

    AbsoluteFloatInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.ABSOLUTE_FLOAT, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        m.getFPR().setFloat(reg1, Math.abs(m.getFPR().getFloat(reg1)));
        return true;
    }

    @Override
    public String instCode() {
        return "ABSOLUTE_FLOAT";
    }
}