package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class XorRegisterInstruction extends Instruction {

    XorRegisterInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.XOR_REGISTER, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        m.getRegister().setInteger(reg1, setFlagsAfterLogicalOp(m.getRegister().getInteger(reg1) ^ m.getRegister().getInteger(reg2), m.getFlagsRegister()));
        return true;
    }
}