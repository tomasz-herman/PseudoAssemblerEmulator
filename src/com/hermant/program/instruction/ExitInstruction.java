package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class ExitInstruction extends Instruction {

    ExitInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(Instruction.EXIT, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        return false;
    }

    @Override
    public String instCode() {
        return "EXIT";
    }
}
