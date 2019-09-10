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
        FileInputStream in = new FileInputStream(FileDescriptor.in);
        try {
            int i = -1;
            while(i != 10)
                if (in.available() > 0) i = in.read();
                else Thread.sleep(1);
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
