package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class DivideRegisterFloatInstruction extends Instruction {

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        m.getFPR().setFloat(reg1, m.getFPR().getFloat(reg1) / m.getFPR().getFloat(reg2));
        return true;
    }

    @Override
    public byte code() {
        return DIVIDE_REGISTER_FLOAT;
    }

    @Override
    public int instLength() {
        return 2;
    }

    @Override
    public String instCode() {
        return "DIVIDE_REGISTER_FLOAT";
    }
}