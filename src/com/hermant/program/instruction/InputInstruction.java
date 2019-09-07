package com.hermant.program.instruction;

import com.hermant.machine.Machine;

import java.io.IOException;

public class InputInstruction extends Instruction implements MemoryOperation {

    @Override
    public boolean execute(Machine m, boolean debug) {
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        try {
            byte input;
            while(true)
                if (System.in.available() > 0) {
                    input = (byte)System.in.read();
                    break;
                }
                else Thread.sleep(1);
            m.getRam().setByte(ramAddress, input);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (InterruptedException ignored) { }
        return true;
    }

    @Override
    public byte code() {
        return INPUT;
    }

    @Override
    public int instLength() {
        return 4;
    }

    @Override
    public String instCode() {
        return "INPUT";
    }
}
