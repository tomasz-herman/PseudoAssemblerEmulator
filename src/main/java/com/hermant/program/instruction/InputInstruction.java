package com.hermant.program.instruction;

import com.hermant.machine.Machine;

import java.io.IOException;

public class InputInstruction extends Instruction implements MemoryOperation {

    @Override
    public final void run(Machine m) {
        setInstructionPointer(m.getInstructionPointer());
        final int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        try {
            byte input;
            if(m.getBuffer().available() > 0){
                input = m.getBuffer().get();
            } else while(true)
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
    }

    @Override
    public final byte code() {
        return INPUT;
    }

    @Override
    public final int instLength() {
        return 4;
    }

    @Override
    public final String instCode() {
        return "INPUT";
    }
}
