package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class SleepRegisterInstruction extends Instruction {

    @Override
    public final boolean run(Machine m) {
        setInstructionPointer(m.getInstructionPointer());
        try {
            Thread.sleep(m.getRegister().get(reg1));
        } catch (InterruptedException ignored) { }
        return true;
    }

    @Override
    public final byte code() {
        return SLEEP_REGISTER;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "SLEEP";
    }
}
