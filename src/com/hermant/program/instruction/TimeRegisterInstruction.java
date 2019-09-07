package com.hermant.program.instruction;

import com.hermant.machine.Machine;
import com.hermant.machine.register.Register;

public class TimeRegisterInstruction extends Instruction {

    @Override
    public final boolean execute(Machine m, boolean debug) {
        if(debug) debug(m.getInstructionPointer());
        setInstructionPointer(m.getInstructionPointer());
        long timeMillis = System.currentTimeMillis();
        m.getRegister().setInteger(reg1, (int)(timeMillis / 1000));
        m.getRegister().setInteger(Register.REMAINDER, (int) Long.remainderUnsigned(timeMillis, 1000));
        return true;
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
