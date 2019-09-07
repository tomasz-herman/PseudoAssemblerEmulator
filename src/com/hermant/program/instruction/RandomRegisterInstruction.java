package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class RandomRegisterInstruction extends Instruction {

    @Override
    public final boolean execute(Machine m, boolean debug){
        if(debug) debug(m.getInstructionPointer());
        setInstructionPointer(m.getInstructionPointer());
        m.getRegister().setInteger(reg1, m.getRng().getNext());
        return true;
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