package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class SineFloatInstruction extends Instruction {

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        m.getFPR().setFloat(reg1, (float)Math.sin(m.getFPR().getFloat(reg1)));
        return true;
    }

    @Override
    public byte code() {
        return SINE_FLOAT;
    }

    @Override
    public int instLength() {
        return 2;
    }

    @Override
    public String instCode() {
        return "SINE_FLOAT";
    }
}