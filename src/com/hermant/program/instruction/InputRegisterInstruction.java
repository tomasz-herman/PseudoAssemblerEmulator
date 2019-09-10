package com.hermant.program.instruction;

import com.hermant.machine.Machine;

import java.io.IOException;

public class InputRegisterInstruction extends Instruction {

    @Override
    public final void run(Machine m) {
        setInstructionPointer(m.getInstructionPointer());
        try {
            byte input;
            while(true)
                if (System.in.available() > 0) {
                    input = (byte)System.in.read();
                    break;
                }
                else Thread.sleep(1);
            m.getRegister().set(reg1, input);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (InterruptedException ignored) { }
    }

    @Override
    public final byte code() {
        return INPUT_REGISTER;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "INPUT_REGISTER";
    }
}
