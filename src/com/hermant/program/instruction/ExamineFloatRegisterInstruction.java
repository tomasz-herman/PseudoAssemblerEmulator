package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class ExamineFloatRegisterInstruction extends Instruction implements FloatArithmeticOperation {

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        examineFloat(m.getFPR().getFloat(reg1), m.getFlagsRegister());
        return true;
    }

    @Override
    public byte code() {
        return EXAMINE_FLOAT_REGISTER;
    }

    @Override
    public int instLength() {
        return 2;
    }

    @Override
    public String instCode() {
        return "EXAMINE_FLOAT_REGISTER";
    }
}