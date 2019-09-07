package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class LeftShiftLogicalRegisterInstruction extends Instruction implements LogicalOperation {

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        m.getRegister().setInteger(reg1, logical(m.getRegister().getInteger(reg1) << m.getRegister().getInteger(reg2), m.getFlagsRegister()));
        return true;
    }

    @Override
    public byte code() {
        return LEFT_SHIFT_LOGICAL_REGISTER;
    }

    @Override
    public int instLength() {
        return 2;
    }

    @Override
    public String instCode() {
        return "LEFT_SHIFT_LOGICAL_REGISTER";
    }
}