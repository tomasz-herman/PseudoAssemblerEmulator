package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class RightRotateRegisterInstruction extends Instruction implements LogicalOperation {

    RightRotateRegisterInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.RIGHT_ROTATE_REGISTER, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        m.getRegister().setInteger(reg1, setFlagsAfterLogicalOp(Integer.rotateRight(m.getRegister().getInteger(reg1), m.getRegister().getInteger(reg2)), m.getFlagsRegister()));
        return true;
    }

    @Override
    public String instCode() {
        return "RIGHT_ROTATE_REGISTER";
    }
}