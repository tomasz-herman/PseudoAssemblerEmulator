package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class LeftRotateRegisterInstruction extends Instruction {

    LeftRotateRegisterInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.LEFT_ROTATE_REGISTER, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        m.getRegister().setInteger(reg1, setFlagsAfterLogicalOp(Integer.rotateLeft(m.getRegister().getInteger(reg1), m.getRegister().getInteger(reg2)), m.getFlagsRegister()));
        return true;
    }
}