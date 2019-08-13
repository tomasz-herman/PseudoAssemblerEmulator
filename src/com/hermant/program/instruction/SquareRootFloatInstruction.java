package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class SquareRootFloatInstruction extends Instruction {

    SquareRootFloatInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(Instruction.SQUARE_ROOT_FLOAT, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        m.getFPR().setFloat(reg1, (float)Math.sqrt(m.getFPR().getFloat(reg1)));
        return true;
    }

    @Override
    public String instCode() {
        return "SQUARE_ROOT_FLOAT";
    }
}