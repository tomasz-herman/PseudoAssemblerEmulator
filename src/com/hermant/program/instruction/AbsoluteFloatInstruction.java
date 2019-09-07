package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class AbsoluteFloatInstruction extends Instruction {

    @Override
    public final boolean execute(Machine m, boolean debug){
        if(debug) debug(m.getInstructionPointer());
        setInstructionPointer(m.getInstructionPointer());
        m.getFPR().setFloat(reg1, Math.abs(m.getFPR().getFloat(reg1)));
        return true;
    }

    @Override
    public final byte code() {
        return ABSOLUTE_FLOAT;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "ABSOLUTE_FLOAT";
    }
}