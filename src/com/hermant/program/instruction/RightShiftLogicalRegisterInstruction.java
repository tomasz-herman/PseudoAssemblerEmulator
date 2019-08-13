package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class RightShiftLogicalRegisterInstruction extends Instruction implements LogicalOperation {

    RightShiftLogicalRegisterInstruction(Byte reg1, Byte reg2, Short ramAddress) {
        super(Instruction.RIGHT_SHIFT_LOGICAL_REGISTER, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        m.getRegister().setInteger(reg1, logical(m.getRegister().getInteger(reg1) >>> m.getRegister().getInteger(reg2), m.getFlagsRegister()));
        return true;
    }

    @Override
    public String instCode() {
        return "RIGHT_SHIFT_LOGICAL_REGISTER";
    }
}