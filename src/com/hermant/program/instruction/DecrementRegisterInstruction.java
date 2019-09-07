package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class DecrementRegisterInstruction extends Instruction implements IntegerArithmeticOperation {

    @Override
    public final boolean execute(Machine m, boolean debug){
        if(debug) debug(m.getInstructionPointer());
        setInstructionPointer(m.getInstructionPointer());
        int a = m.getRegister().getInteger(reg1);
        m.getRegister().setInteger(reg1, decrement(a, m.getFlagsRegister()));
        return true;
    }

    @Override
    public final byte code() {
        return DECREMENT_REGISTER;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "DECREMENT_REGISTER";
    }
}