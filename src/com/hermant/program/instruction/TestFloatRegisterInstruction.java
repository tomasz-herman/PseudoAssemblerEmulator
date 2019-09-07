package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class TestFloatRegisterInstruction extends Instruction implements FloatArithmeticOperation {

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        compareFloat(m.getFPR().getFloat(reg1), 0f, m.getFlagsRegister());
        return true;
    }

    @Override
    public byte code() {
        return TEST_FLOAT_REGISTER;
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