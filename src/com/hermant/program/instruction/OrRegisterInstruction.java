package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class OrRegisterInstruction extends Instruction implements LogicalOperation {

    OrRegisterInstruction(Byte reg1, Byte reg2, Short ramAddress) {
        super(Instruction.OR_REGISTER, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        m.getRegister().setInteger(reg1, logical(m.getRegister().getInteger(reg1) | m.getRegister().getInteger(reg2), m.getFlagsRegister()));
        return true;
    }

    @Override
    public String instCode() {
        return "OR_REGISTER";
    }
}