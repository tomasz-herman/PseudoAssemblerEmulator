package com.hermant.program.instruction;

import com.hermant.machine.Machine;
import com.hermant.machine.Stack;
import com.hermant.machine.register.Register;

public class PushAllInstruction extends Instruction {

    PushAllInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(PUSH_ALL, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug) {
        super.execute(m, debug);
        Stack s = m.getStack();
        Register r = m.getRegister();
        int sp = r.getInteger(Register.STACK_POINTER);
        int i;
        for (i = 0; i < Register.STACK_POINTER; i++) s.push(r.getInteger(i));
        s.push(sp);
        for (i++; i < Register.REGISTER_SIZE; i++) s.push(r.getInteger(i));
        return true;
    }

    @Override
    public int instLength() {
        return 2;
    }

    @Override
    public String instCode() {
        return "PUSH_ALL";
    }
}
