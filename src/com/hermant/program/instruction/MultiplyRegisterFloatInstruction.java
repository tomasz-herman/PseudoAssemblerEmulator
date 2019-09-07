package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class MultiplyRegisterFloatInstruction extends Instruction {

    @Override
    public final boolean execute(Machine m, boolean debug){
        if(debug) debug(m.getInstructionPointer());
        setInstructionPointer(m.getInstructionPointer());
        m.getFPR().setFloat(reg1, m.getFPR().getFloat(reg1) * m.getFPR().getFloat(reg2));
        return true;
    }

    @Override
    public final byte code() {
        return MULTIPLY_REGISTER_FLOAT;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "MULTIPLY_REGISTER_FLOAT";
    }
}