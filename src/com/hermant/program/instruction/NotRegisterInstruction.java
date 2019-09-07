package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class NotRegisterInstruction extends Instruction {

    @Override
    public final boolean run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        m.getRegister().setInteger(reg1, ~m.getRegister().getInteger(reg1));
        return true;
    }

    @Override
    public final byte code() {
        return NOT_REGISTER;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "NOT_REGISTER";
    }
}