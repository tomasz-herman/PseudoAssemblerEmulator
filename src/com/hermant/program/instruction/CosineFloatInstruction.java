package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class CosineFloatInstruction extends Instruction {

    CosineFloatInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(Instruction.COSINE_FLOAT, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        m.getFPR().setFloat(reg1, (float)Math.cos(m.getFPR().getFloat(reg1)));
        return true;
    }

    @Override
    public String instCode() {
        return "COSINE_FLOAT";
    }
}