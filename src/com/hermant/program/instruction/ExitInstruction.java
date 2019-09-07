package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class ExitInstruction extends Instruction {

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        return false;
    }

    @Override
    public byte code() {
        return EXIT;
    }

    @Override
    public int instLength() {
        return 2;
    }

    @Override
    public String instCode() {
        return "EXIT";
    }
}
