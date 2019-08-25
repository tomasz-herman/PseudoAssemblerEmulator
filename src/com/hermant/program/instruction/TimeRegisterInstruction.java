package com.hermant.program.instruction;

import com.hermant.machine.Machine;
import com.hermant.machine.register.Register;

public class TimeRegisterInstruction extends Instruction {

    TimeRegisterInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(TIME_REGISTER, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug) {
        super.execute(m, debug);
        long timeMillis = System.currentTimeMillis();
        m.getRegister().setInteger(reg1, (int)(timeMillis / 1000));
        m.getRegister().setInteger(Register.REMAINDER, (int) Long.remainderUnsigned(timeMillis, 1000));
        return true;
    }

    @Override
    public int instLength() {
        return 2;
    }

    @Override
    public String instCode() {
        return "TIME_REGISTER";
    }
}
