package com.hermant.program.instruction;

import com.hermant.machine.register.InstructionPointer;

public abstract class OutputOperation extends Instruction{

    public OutputOperation(byte code, Byte reg1, Byte reg2, Short ramAddress) {
        super(code, reg1, reg2, ramAddress);
    }

    @Override
    public void debug(InstructionPointer instructionPointer){
        System.out.print(String.format("%1$08X",instructionPointer.get()) + " | " + this);
    }
}
