package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class RightShiftArithmeticRegisterInstruction extends Instruction implements LogicalOperation {

    RightShiftArithmeticRegisterInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.RIGHT_SHIFT_ARITHMETIC_REGISTER, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        m.getRegister().setInteger(reg1, setFlagsAfterLogicalOp(m.getRegister().getInteger(reg1) >> m.getRegister().getInteger(reg2), m.getFlagsRegister()));
        return true;
    }

    @Override
    public String instCode() {
        return "RIGHT_SHIFT_ARITHMETIC_REGISTER";
    }
}