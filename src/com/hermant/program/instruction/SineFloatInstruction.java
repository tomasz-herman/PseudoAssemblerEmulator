package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class SineFloatInstruction extends Instruction {

    @Override
    public final boolean execute(Machine m, boolean debug){
        if(debug) debug(m.getInstructionPointer());
        setInstructionPointer(m.getInstructionPointer());
        m.getFPR().setFloat(reg1, (float)Math.sin(m.getFPR().getFloat(reg1)));
        return true;
    }

    @Override
    public final byte code() {
        return SINE_FLOAT;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "SINE_FLOAT";
    }
}