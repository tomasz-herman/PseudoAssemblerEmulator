package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class DivideRegisterFloatInstruction extends Instruction {

    DivideRegisterFloatInstruction(Byte reg1, Byte reg2, Short ramAddress) {
        super(Instruction.DIVIDE_REGISTER_FLOAT, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        m.getFPR().setFloat(reg1, m.getFPR().getFloat(reg1) / m.getFPR().getFloat(reg2));
        return true;
    }

    @Override
    public String instCode() {
        return "DIVIDE_REGISTER_FLOAT";
    }
}