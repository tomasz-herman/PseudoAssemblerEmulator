package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class AndRegisterInstruction extends Instruction implements LogicalOperation {

    AndRegisterInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(Instruction.AND_REGISTER, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        m.getRegister().setInteger(reg1, logical(m.getRegister().getInteger(reg1) & m.getRegister().getInteger(reg2), m.getFlagsRegister()));
        return true;
    }

    @Override
    public int instLength() {
        return 2;
    }

    @Override
    public String instCode() {
        return "AND_REGISTER";
    }
}