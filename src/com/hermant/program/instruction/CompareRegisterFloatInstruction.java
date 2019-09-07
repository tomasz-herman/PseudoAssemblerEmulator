package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class CompareRegisterFloatInstruction extends Instruction implements FloatArithmeticOperation {

    @Override
    public final boolean run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        compareFloat(m.getFPR().getFloat(reg1), m.getFPR().getFloat(reg2), m.getFlagsRegister());
        return true;
    }

    @Override
    public final byte code() {
        return COMPARE_REGISTER_FLOAT;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "COMPARE_REGISTER_FLOAT";
    }
}