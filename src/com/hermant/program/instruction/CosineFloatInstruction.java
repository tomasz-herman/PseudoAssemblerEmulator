package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class CosineFloatInstruction extends Instruction {

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        m.getFPR().setFloat(reg1, (float)Math.cos(m.getFPR().getFloat(reg1)));
        return true;
    }

    @Override
    public byte code() {
        return COSINE_FLOAT;
    }

    @Override
    public int instLength() {
        return 2;
    }

    @Override
    public String instCode() {
        return "COSINE_FLOAT";
    }
}