package com.hermant.program.instruction;

import com.hermant.machine.Machine;
import com.hermant.machine.Stack;
import com.hermant.machine.register.GeneralPurposeRegister;

public class PushAllInstruction extends Instruction {

    @Override
    public final void run(Machine m) {
        setInstructionPointer(m.getInstructionPointer());
        Stack s = m.getStack();
        GeneralPurposeRegister r = m.getRegister();
        int sp = r.get(GeneralPurposeRegister.STACK_POINTER);
        int i;
        for (i = 0; i < GeneralPurposeRegister.STACK_POINTER; i++) s.push(r.get(i));
        s.push(sp);
        for (i++; i < GeneralPurposeRegister.REGISTER_SIZE; i++) s.push(r.get(i));
    }

    @Override
    public final byte code() {
        return PUSH_ALL;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "PUSH_ALL";
    }
}
