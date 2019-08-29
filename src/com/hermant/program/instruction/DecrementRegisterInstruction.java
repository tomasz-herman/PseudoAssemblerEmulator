package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class DecrementRegisterInstruction extends Instruction implements IntegerArithmeticOperation {

    DecrementRegisterInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(DECREMENT_REGISTER, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int a = m.getRegister().getInteger(reg1);
        m.getRegister().setInteger(reg1, decrement(a, m.getFlagsRegister()));
        return true;
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