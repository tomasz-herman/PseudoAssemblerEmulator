package com.hermant.program.instruction;

import com.hermant.machine.Machine;

import java.io.*;

public class HaltInstruction extends Instruction {

    HaltInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(HALT, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug) {
        super.execute(m, debug);
        FileInputStream in = new FileInputStream(FileDescriptor.in);
        try {
            int i = -1;
            while(i != 10) i = in.read();
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
        return "HALT";
    }
}
