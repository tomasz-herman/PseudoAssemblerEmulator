package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class PopRegisterFloatInstruction extends Instruction {

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        m.getFPR().setInteger(reg1, m.getStack().pop());
        return true;
    }

    @Override
    public byte code() {
        return POP_REGISTER_FLOAT;
    }

    @Override
    public int instLength() {
        return 2;
    }

    @Override
    public String instCode() {
        return "POP_REGISTER_FLOAT";
    }
}
