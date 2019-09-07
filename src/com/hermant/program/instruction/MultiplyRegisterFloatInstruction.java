package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class MultiplyRegisterFloatInstruction extends Instruction {

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        m.getFPR().setFloat(reg1, m.getFPR().getFloat(reg1) * m.getFPR().getFloat(reg2));
        return true;
    }

    @Override
    public byte code() {
        return MULTIPLY_REGISTER_FLOAT;
    }

    @Override
    public int instLength() {
        return 2;
    }

    @Override
    public String instCode() {
        return "MULTIPLY_REGISTER_FLOAT";
    }
}