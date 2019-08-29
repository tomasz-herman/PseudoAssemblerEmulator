package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class CompareRegisterFloatInstruction extends Instruction implements FloatArithmeticOperation {

    CompareRegisterFloatInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(COMPARE_REGISTER_FLOAT, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        compareFloat(m.getFPR().getFloat(reg1), m.getFPR().getFloat(reg2), m.getFlagsRegister());
        return true;
    }

    @Override
    public int instLength() {
        return 2;
    }

    @Override
    public String instCode() {
        return "COMPARE_REGISTER_FLOAT";
    }
}