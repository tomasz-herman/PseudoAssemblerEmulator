package com.hermant.program.instruction;

import com.hermant.machine.Machine;

import java.io.*;

/**
 * read() can't be interrupted by Thread.interrupt().
 * https://bugs.java.com/bugdatabase/view_bug.do?bug_id=4514257
 */
public class HaltInstruction extends Instruction {

    @Override
    public boolean execute(Machine m, boolean debug) {
        super.execute(m, debug);
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
        return true;
    }

    @Override
    public byte code() {
        return HALT;
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
