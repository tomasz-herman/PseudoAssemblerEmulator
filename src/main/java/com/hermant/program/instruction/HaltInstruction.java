package com.hermant.program.instruction;

import com.hermant.machine.Machine;

import java.io.*;

/**
 * read() can't be interrupted by Thread.interrupt().
 * https://bugs.java.com/bugdatabase/view_bug.do?bug_id=4514257
 */
public class HaltInstruction extends Instruction {

    @Override
    public final void run(Machine m) {
        setInstructionPointer(m.getInstructionPointer());
        InputStream in = System.in;
        try {
            while (in.available() > 0) m.getBuffer().put((byte)in.read());
            int i = -1;
            while(i != '\n')
                if (in.available() > 0) {
                    i = in.read();
                    if (i != -1 && i != '\n') m.getBuffer().put((byte) i);
                } else Thread.sleep(1);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (InterruptedException ignored) { }
    }

    @Override
    public final byte code() {
        return HALT;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "HALT";
    }
}
