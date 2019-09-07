package com.hermant.program.instruction;

import com.hermant.machine.Machine;

import java.io.IOException;

public class InputRegisterInstruction extends Instruction {

    @Override
    public boolean execute(Machine m, boolean debug) {
        super.execute(m, debug);
        try {
            byte input;
            while(true)
                if (System.in.available() > 0) {
                    input = (byte)System.in.read();
                    break;
                }
                else Thread.sleep(1);
            m.getRegister().setInteger(reg1, input);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (InterruptedException ignored) { }
        return true;
    }

    @Override
    public byte code() {
        return INPUT_REGISTER;
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
