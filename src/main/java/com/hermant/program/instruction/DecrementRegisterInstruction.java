package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class DecrementRegisterInstruction extends Instruction implements IntegerArithmeticOperation {

    @Override
    public final void run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        int a = m.getRegister().get(reg1);
        m.getRegister().set(reg1, decrement(a, m.getFlagsRegister()));
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