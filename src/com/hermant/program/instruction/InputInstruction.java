package com.hermant.program.instruction;

import com.hermant.machine.Machine;

import java.io.IOException;

public class InputInstruction extends Instruction implements MemoryOperation{

    InputInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(INPUT, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug) {
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        try {
            m.getRam().setByte(ramAddress, (byte)System.in.read());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
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
