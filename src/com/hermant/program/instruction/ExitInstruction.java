package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class ExitInstruction extends Instruction {

    @Override
    public final boolean run(Machine m){
        return false;
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
