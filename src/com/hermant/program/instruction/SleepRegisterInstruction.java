package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class SleepRegisterInstruction extends Instruction {

    @Override
    public boolean execute(Machine m, boolean debug) {
        super.execute(m, debug);
        try {
            Thread.sleep(m.getRegister().getInteger(reg1));
        } catch (InterruptedException ignored) { }
        return true;
    }

    @Override
    public byte code() {
        return SLEEP_REGISTER;
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
