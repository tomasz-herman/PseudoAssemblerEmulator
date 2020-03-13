package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class RandomRegisterInstruction extends Instruction {

    @Override
    public final void run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        m.getRegister().set(reg1, m.getRng().getNext());
    }

    @Override
    public final byte code() {
        return RANDOM_REGISTER;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "RANDOM_REGISTER";
    }
}