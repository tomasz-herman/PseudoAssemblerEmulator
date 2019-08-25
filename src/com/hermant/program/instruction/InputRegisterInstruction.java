package com.hermant.program.instruction;

import com.hermant.machine.Machine;

import java.io.IOException;

public class InputRegisterInstruction extends Instruction {

    InputRegisterInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(INPUT_REGISTER, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug) {
        super.execute(m, debug);
        try {
            m.getRegister().setInteger(reg1, System.in.read());
        } catch (IOException e) {
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
        return "INPUT_REGISTER";
    }
}
