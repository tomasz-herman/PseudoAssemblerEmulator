package com.hermant.program.instruction;

import com.hermant.machine.Machine;
import com.hermant.machine.Stack;
import com.hermant.machine.register.Register;

public class PushAllFloatInstruction extends Instruction {

    @Override
    public final boolean execute(Machine m, boolean debug) {
        if(debug) debug(m.getInstructionPointer());
        setInstructionPointer(m.getInstructionPointer());
        Stack s = m.getStack();
        Register fpr = m.getFPR();
        for (int i = 0; i < Register.REGISTER_SIZE; i++) s.push(fpr.getInteger(i));
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
