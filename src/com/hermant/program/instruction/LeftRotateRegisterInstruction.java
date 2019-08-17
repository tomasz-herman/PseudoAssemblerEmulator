package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class LeftRotateRegisterInstruction extends Instruction implements LogicalOperation {

    LeftRotateRegisterInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(Instruction.LEFT_ROTATE_REGISTER, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        m.getRegister().setInteger(reg1, logical(Integer.rotateLeft(m.getRegister().getInteger(reg1), m.getRegister().getInteger(reg2)), m.getFlagsRegister()));
        return true;
    }

    @Override
    public int instLength() {
        return 2;
    }

    @Override
    public String instCode() {
        return "LEFT_ROTATE_REGISTER";
    }
}