package com.hermant.program.instruction;

import com.hermant.machine.Register;

import static com.hermant.machine.Register.INSTRUCTION_POINTER;

public abstract class OutputOperation extends Instruction{

    public OutputOperation(byte code, Byte reg1, Byte reg2, Integer ramAddress) {
        super(code, reg1, reg2, ramAddress);
    }

    @Override
    public void debug(Register reg){
        System.out.print(String.format("%1$08X",reg.getInteger(INSTRUCTION_POINTER)) + " | " + this);
    }
}
