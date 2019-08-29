package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class SubtractRegisterFloatInstruction extends Instruction {

    SubtractRegisterFloatInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(SUBTRACT_REGISTER_FLOAT, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        m.getFPR().setFloat(reg1, m.getFPR().getFloat(reg1) - m.getFPR().getFloat(reg2));
        return true;
    }

    @Override
    public int instLength() {
        return 2;
    }

    @Override
    public String instCode() {
        return "SUBTRACT_REGISTER_FLOAT";
    }
}