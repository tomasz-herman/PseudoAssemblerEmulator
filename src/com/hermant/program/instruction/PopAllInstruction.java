package com.hermant.program.instruction;

import com.hermant.machine.Machine;
import com.hermant.machine.Stack;
import com.hermant.machine.register.Register;

public class PopAllInstruction extends Instruction {

    @Override
    public boolean execute(Machine m, boolean debug) {
        super.execute(m, debug);
        Stack s = m.getStack();
        Register r = m.getRegister();
        int i;
        for (i = Register.REGISTER_SIZE - 1; i > Register.STACK_POINTER; i--) r.setInteger(i, s.pop());
        s.pop();
        for (i--; i >= 0; i--) r.setInteger(i, s.pop());
        return true;
    }

    @Override
    public byte code() {
        return POP_ALL;
    }

    @Override
    public int instLength() {
        return 2;
    }

    @Override
    public String instCode() {
        return "POP_ALL";
    }
}
