package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class NegateRegisterInstruction extends Instruction implements IntegerArithmeticOperation{

    @Override
    public final void run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        int a = m.getRegister().get(reg1);
        m.getRegister().set(reg1, negate(a, m.getFlagsRegister()));
    }

    @Override
    public final byte code() {
        return NEGATE_REGISTER;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "NEGATE_REGISTER";
    }
}