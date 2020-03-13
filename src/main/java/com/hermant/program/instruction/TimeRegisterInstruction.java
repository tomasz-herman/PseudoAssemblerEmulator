package com.hermant.program.instruction;

import com.hermant.machine.Machine;
import com.hermant.machine.register.GeneralPurposeRegister;

public class TimeRegisterInstruction extends Instruction {

    @Override
    public final void run(Machine m) {
        setInstructionPointer(m.getInstructionPointer());
        long timeMillis = System.currentTimeMillis();
        m.getRegister().set(reg1, (int)(timeMillis / 1000));
        m.getRegister().set(GeneralPurposeRegister.REMAINDER, (int) Long.remainderUnsigned(timeMillis, 1000));
    }

    @Override
    public final byte code() {
        return TIME_REGISTER;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "TIME_REGISTER";
    }
}
