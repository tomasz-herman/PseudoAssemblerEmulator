package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class ExitInstruction extends Instruction {

    @Override
    public final void run(Machine m){
        m.stop();
    }

    @Override
    public final byte code() {
        return EXIT;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "EXIT";
    }
}
