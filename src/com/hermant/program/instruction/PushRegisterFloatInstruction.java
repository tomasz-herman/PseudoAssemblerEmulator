package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class PushRegisterFloatInstruction extends Instruction {

    @Override
    public final boolean run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        m.getStack().push(m.getFPR().getInteger(reg1));
        return true;
    }

    @Override
    public final byte code() {
        return PUSH_REGISTER_FLOAT;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "PUSH_REGISTER_FLOAT";
    }
}
