package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class TangentFloatInstruction extends Instruction {

    TangentFloatInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.TANGENT_FLOAT, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        m.getFPR().setFloat(reg1, (float)Math.tan(m.getFPR().getFloat(reg1)));
        return true;
    }

    @Override
    public String instCode() {
        return "TANGENT_FLOAT";
    }
}