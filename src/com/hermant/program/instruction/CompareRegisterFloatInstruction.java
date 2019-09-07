package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class CompareRegisterFloatInstruction extends Instruction implements FloatArithmeticOperation {

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        compareFloat(m.getFPR().getFloat(reg1), m.getFPR().getFloat(reg2), m.getFlagsRegister());
        return true;
    }

    @Override
    public byte code() {
        return COMPARE_REGISTER_FLOAT;
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