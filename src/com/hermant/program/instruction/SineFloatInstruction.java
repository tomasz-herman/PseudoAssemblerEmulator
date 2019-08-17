package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class SineFloatInstruction extends Instruction {

    SineFloatInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(Instruction.SINE_FLOAT, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        m.getFPR().setFloat(reg1, (float)Math.sin(m.getFPR().getFloat(reg1)));
        return true;
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