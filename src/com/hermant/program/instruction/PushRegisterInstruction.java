package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class PushRegisterInstruction extends Instruction {

    PushRegisterInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(Instruction.PUSH_REGISTER, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        m.getStack().push(m.getRegister().getInteger(reg1));
        return true;
    }

    @Override
    public String instCode() {
        return "PUSH_REGISTER";
    }
}
