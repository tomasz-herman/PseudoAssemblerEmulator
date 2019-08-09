package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class SquareRootFloatInstruction extends Instruction {

    SquareRootFloatInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.SQUARE_ROOT_FLOAT, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        m.getFPR().setFloat(reg1, (float)Math.sqrt(m.getFPR().getFloat(reg1)));
        return true;
    }
}