package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class NegateRegisterInstruction extends Instruction implements IntegerArithmeticOperation{

    @Override
    public final boolean execute(Machine m, boolean debug){
        if(debug) debug(m.getInstructionPointer());
        setInstructionPointer(m.getInstructionPointer());
        int a = m.getRegister().getInteger(reg1);
        m.getRegister().setInteger(reg1, negate(a, m.getFlagsRegister()));
        return true;
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