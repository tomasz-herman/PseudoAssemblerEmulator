package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class NegateRegisterInstruction extends Instruction implements IntegerArithmeticOperation{

    NegateRegisterInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(Instruction.NEGATE_REGISTER, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int a = m.getRegister().getInteger(reg1);
        m.getRegister().setInteger(reg1, negate(a, m.getFlagsRegister()));
        return true;
    }

    @Override
    public String instCode() {
        return "NEGATE_REGISTER";
    }
}