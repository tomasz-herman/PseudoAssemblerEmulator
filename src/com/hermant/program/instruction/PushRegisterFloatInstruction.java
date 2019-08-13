package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class PushRegisterFloatInstruction extends Instruction {

    PushRegisterFloatInstruction(Byte reg1, Byte reg2, Short ramAddress) {
        super(Instruction.PUSH_REGISTER_FLOAT, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        m.getStack().push(m.getFPR().getInteger(reg1));
        return true;
    }

    @Override
    public String instCode() {
        return "PUSH_REGISTER_FLOAT";
    }
}
