package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class ExamineFloatRegisterInstruction extends Instruction implements FloatArithmeticOperation {

    @Override
    public final boolean execute(Machine m, boolean debug){
        if(debug) debug(m.getInstructionPointer());
        setInstructionPointer(m.getInstructionPointer());
        examineFloat(m.getFPR().getFloat(reg1), m.getFlagsRegister());
        return true;
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