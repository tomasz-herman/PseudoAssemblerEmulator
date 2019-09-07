package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class NegateRegisterInstruction extends Instruction implements IntegerArithmeticOperation{

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int a = m.getRegister().getInteger(reg1);
        m.getRegister().setInteger(reg1, negate(a, m.getFlagsRegister()));
        return true;
    }

    @Override
    public byte code() {
        return NEGATE_REGISTER;
    }

    @Override
    public int instLength() {
        return 2;
    }

    @Override
    public String instCode() {
        return "NEGATE_REGISTER";
    }
}