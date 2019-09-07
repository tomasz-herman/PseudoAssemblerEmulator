package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class DecrementRegisterInstruction extends Instruction implements IntegerArithmeticOperation {

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int a = m.getRegister().getInteger(reg1);
        m.getRegister().setInteger(reg1, decrement(a, m.getFlagsRegister()));
        return true;
    }

    @Override
    public byte code() {
        return DECREMENT_REGISTER;
    }

    @Override
    public int instLength() {
        return 2;
    }

    @Override
    public String instCode() {
        return "DECREMENT_REGISTER";
    }
}