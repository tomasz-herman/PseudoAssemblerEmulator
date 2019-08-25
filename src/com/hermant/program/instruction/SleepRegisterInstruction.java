package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class SleepRegisterInstruction extends Instruction {

    SleepRegisterInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(SLEEP_REGISTER, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug) {
        super.execute(m, debug);
        try {
            Thread.sleep(m.getRegister().getInteger(reg1));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public int instLength() {
        return 2;
    }

    @Override
    public String instCode() {
        return "SLEEP";
    }
}
