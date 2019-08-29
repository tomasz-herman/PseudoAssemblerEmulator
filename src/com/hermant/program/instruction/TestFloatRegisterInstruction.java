package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class TestFloatRegisterInstruction extends Instruction implements FloatArithmeticOperation {

    TestFloatRegisterInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(TEST_FLOAT_REGISTER, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        compareFloat(m.getFPR().getFloat(reg1), 0f, m.getFlagsRegister());
        return true;
    }

    @Override
    public int instLength() {
        return 2;
    }

    @Override
    public String instCode() {
        return "TEST_FLOAT_REGISTER";
    }
}