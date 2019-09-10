package com.hermant.program.instruction;

import com.hermant.machine.Machine;
import com.hermant.machine.Stack;
import com.hermant.machine.register.GeneralPurposeRegister;

public class PopAllInstruction extends Instruction {

    @Override
    public final boolean run(Machine m) {
        setInstructionPointer(m.getInstructionPointer());
        Stack s = m.getStack();
        GeneralPurposeRegister r = m.getRegister();
        int i;
        for (i = GeneralPurposeRegister.REGISTER_SIZE - 1; i > GeneralPurposeRegister.STACK_POINTER; i--) r.set(i, s.pop());
        s.pop();
        for (i--; i >= 0; i--) r.set(i, s.pop());
        return true;
    }

    @Override
    public final byte code() {
        return POP_ALL;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "POP_ALL";
    }
}
