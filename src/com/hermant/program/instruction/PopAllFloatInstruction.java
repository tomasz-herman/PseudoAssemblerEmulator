package com.hermant.program.instruction;

import com.hermant.machine.Machine;
import com.hermant.machine.Stack;
import com.hermant.machine.register.Register;

public class PopAllFloatInstruction extends Instruction {

    @Override
    public final boolean run(Machine m) {
        setInstructionPointer(m.getInstructionPointer());
        Stack s = m.getStack();
        Register fpr = m.getFPR();
        for (int i = Register.REGISTER_SIZE - 1; i >= 0; i--) fpr.setInteger(i, s.pop());
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
