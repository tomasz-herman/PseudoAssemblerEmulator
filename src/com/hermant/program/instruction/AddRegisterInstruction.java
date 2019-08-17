package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class AddRegisterInstruction extends Instruction implements IntegerArithmeticOperation {

    AddRegisterInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(Instruction.ADD_REGISTER, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int a = m.getRegister().getInteger(reg1);
        int b = m.getRegister().getInteger(reg2);
        m.getRegister().setInteger(reg1, add(a, b, m.getFlagsRegister()));
        return true;
    }

    @Override
    public int instLength() {
        return 2;
    }

    @Override
    public String instCode() {
        return "ADD_REGISTER";
    }
}