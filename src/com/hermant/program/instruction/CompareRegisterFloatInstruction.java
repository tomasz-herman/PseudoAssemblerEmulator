package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class CompareRegisterFloatInstruction extends Instruction implements FloatArithmetic {

    CompareRegisterFloatInstruction(Byte reg1, Byte reg2, Short ramAddress) {
        super(Instruction.COMPARE_REGISTER_FLOAT, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        compareFloat(m.getFPR().getFloat(reg1), m.getFPR().getFloat(reg2), m.getFlagsRegister());
        return true;
    }

    @Override
    public String instCode() {
        return "COMPARE_REGISTER_FLOAT";
    }
}