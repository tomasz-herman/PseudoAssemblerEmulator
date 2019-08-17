package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class IncrementRegisterInstruction extends Instruction implements IntegerArithmeticOperation{

    IncrementRegisterInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(Instruction.INCREMENT_REGISTER, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int a = m.getRegister().getInteger(reg1);
        m.getRegister().setInteger(reg1, increment(a, m.getFlagsRegister()));
        return true;
    }

    @Override
    public int instLength() {
        return 2;
    }

    @Override
    public String instCode() {
        return "INCREMENT_REGISTER";
    }
}