package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class NoOperationInstruction extends Instruction {

    NoOperationInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.NO_OPERATION, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        return true;
    }
}