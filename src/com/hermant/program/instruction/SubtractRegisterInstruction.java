package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class SubtractRegisterInstruction extends Instruction implements IntegerArithmeticOperation {

    SubtractRegisterInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(SUBTRACT_REGISTER, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int a = m.getRegister().getInteger(reg1);
        int b = m.getRegister().getInteger(reg2);
        m.getRegister().setInteger(reg1, subtract(a, b, m.getFlagsRegister()));
        return true;
    }

    @Override
    public int instLength() {
        return 2;
    }

    @Override
    public String instCode() {
        return "SUBTRACT_REGISTER";
    }
}