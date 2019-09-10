package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class TestFloatRegisterInstruction extends Instruction implements FloatArithmeticOperation {

    @Override
    public final void run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        compareFloat(m.getFPR().get(reg1), 0f, m.getFlagsRegister());
    }

    @Override
    public final byte code() {
        return TEST_FLOAT_REGISTER;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "TEST_FLOAT_REGISTER";
    }
}