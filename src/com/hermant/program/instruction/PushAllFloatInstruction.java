package com.hermant.program.instruction;

import com.hermant.machine.Machine;
import com.hermant.machine.Stack;
import com.hermant.machine.register.FloatingPointRegister;
import com.hermant.machine.register.GeneralPurposeRegister;

public class PushAllFloatInstruction extends Instruction {

    @Override
    public final boolean run(Machine m) {
        setInstructionPointer(m.getInstructionPointer());
        Stack s = m.getStack();
        FloatingPointRegister fpr = m.getFPR();
        for (int i = 0; i < FloatingPointRegister.REGISTER_SIZE; i++) s.pushFloat(fpr.get(i));
        return true;
    }

    @Override
    public final byte code() {
        return PUSH_ALL_FLOAT;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "PUSH_ALL_FLOAT";
    }
}
