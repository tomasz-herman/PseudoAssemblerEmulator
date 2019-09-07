package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class SquareRootFloatInstruction extends Instruction {

    @Override
    public final boolean run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        m.getFPR().setFloat(reg1, (float)Math.sqrt(m.getFPR().getFloat(reg1)));
        return true;
    }

    @Override
    public final byte code() {
        return SQUARE_ROOT_FLOAT;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "SQUARE_ROOT_FLOAT";
    }
}