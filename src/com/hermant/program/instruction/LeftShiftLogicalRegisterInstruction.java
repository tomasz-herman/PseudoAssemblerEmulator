package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class LeftShiftLogicalRegisterInstruction extends Instruction {

    LeftShiftLogicalRegisterInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.LEFT_SHIFT_LOGICAL_REGISTER, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        m.getRegister().setInteger(reg1, setFlagsAfterLogicalOp(m.getRegister().getInteger(reg1) << m.getRegister().getInteger(reg2), m.getFlagsRegister()));
        return true;
    }
}