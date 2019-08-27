package com.hermant.program.instruction;

import com.hermant.machine.Machine;
import com.hermant.machine.Stack;
import com.hermant.machine.register.Register;

public class PopAllFloatInstruction extends Instruction {

    PopAllFloatInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(POP_ALL_FLOAT, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug) {
        super.execute(m, debug);
        Stack s = m.getStack();
        Register fpr = m.getFPR();
        for (int i = Register.REGISTER_SIZE - 1; i >= 0; i--) fpr.setInteger(i, s.pop());
        return true;
    }

    @Override
    public int instLength() {
        return 2;
    }

    @Override
    public String instCode() {
        return "POP_ALL_FLOAT";
    }
}