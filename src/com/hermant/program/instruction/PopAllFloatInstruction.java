package com.hermant.program.instruction;

import com.hermant.machine.Machine;
import com.hermant.machine.Stack;
import com.hermant.machine.register.FloatingPointRegister;
import com.hermant.machine.register.GeneralPurposeRegister;

public class PopAllFloatInstruction extends Instruction {

    @Override
    public final boolean run(Machine m) {
        setInstructionPointer(m.getInstructionPointer());
        Stack s = m.getStack();
        FloatingPointRegister fpr = m.getFPR();
        for (int i = GeneralPurposeRegister.REGISTER_SIZE - 1; i >= 0; i--) fpr.set(i, s.popFloat());
        return true;
    }

    @Override
    public final byte code() {
        return POP_ALL_FLOAT;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "POP_ALL_FLOAT";
    }
}
