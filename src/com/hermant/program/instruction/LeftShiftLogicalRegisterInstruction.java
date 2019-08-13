package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class LeftShiftLogicalRegisterInstruction extends Instruction implements LogicalOperation {

    LeftShiftLogicalRegisterInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(Instruction.LEFT_SHIFT_LOGICAL_REGISTER, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        m.getRegister().setInteger(reg1, logical(m.getRegister().getInteger(reg1) << m.getRegister().getInteger(reg2), m.getFlagsRegister()));
        return true;
    }

    @Override
    public String instCode() {
        return "LEFT_SHIFT_LOGICAL_REGISTER";
    }
}