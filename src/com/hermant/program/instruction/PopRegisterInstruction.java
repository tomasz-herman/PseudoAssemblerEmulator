package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class PopRegisterInstruction extends Instruction {

    PopRegisterInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(Instruction.POP_REGISTER, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        m.getRegister().setInteger(reg1, m.getStack().pop());
        return true;
    }

    @Override
    public String instCode() {
        return "POP_REGISTER";
    }
}
