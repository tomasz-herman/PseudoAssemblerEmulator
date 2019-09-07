package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class PushRegisterFloatInstruction extends Instruction {

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        m.getStack().push(m.getFPR().getInteger(reg1));
        return true;
    }

    @Override
    public byte code() {
        return PUSH_REGISTER_FLOAT;
    }

    @Override
    public int instLength() {
        return 2;
    }

    @Override
    public String instCode() {
        return "PUSH_REGISTER_FLOAT";
    }
}
