package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class DivideRegisterFloatInstruction extends Instruction {

    @Override
    public final boolean run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        m.getFPR().set(reg1, m.getFPR().get(reg1) / m.getFPR().get(reg2));
        return true;
    }

    @Override
    public final byte code() {
        return DIVIDE_REGISTER_FLOAT;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "DIVIDE_REGISTER_FLOAT";
    }
}