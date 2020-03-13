package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class CompareRegisterFloatInstruction extends Instruction implements FloatArithmeticOperation {

    @Override
    public final void run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        compareFloat(m.getFPR().get(reg1), m.getFPR().get(reg2), m.getFlagsRegister());
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