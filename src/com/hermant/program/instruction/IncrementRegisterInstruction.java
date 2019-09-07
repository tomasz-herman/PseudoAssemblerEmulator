package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class IncrementRegisterInstruction extends Instruction implements IntegerArithmeticOperation{

    @Override
    public final boolean execute(Machine m, boolean debug){
        if(debug) debug(m.getInstructionPointer());
        setInstructionPointer(m.getInstructionPointer());
        int a = m.getRegister().getInteger(reg1);
        m.getRegister().setInteger(reg1, increment(a, m.getFlagsRegister()));
        return true;
    }

    @Override
    public final byte code() {
        return INCREMENT_REGISTER;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "INCREMENT_REGISTER";
    }
}