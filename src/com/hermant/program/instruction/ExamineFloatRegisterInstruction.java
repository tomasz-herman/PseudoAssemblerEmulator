package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class ExamineFloatRegisterInstruction extends Instruction implements FloatArithmeticOperation {

    @Override
    public final void run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        examineFloat(m.getFPR().get(reg1), m.getFlagsRegister());
    }

    @Override
    public final byte code() {
        return EXAMINE_FLOAT_REGISTER;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "EXAMINE_FLOAT_REGISTER";
    }
}