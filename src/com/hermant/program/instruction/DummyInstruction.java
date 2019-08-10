package com.hermant.program.instruction;

import com.hermant.machine.*;

public class DummyInstruction extends Instruction {

    public DummyInstruction(byte code, Byte reg1, Byte reg2, Integer ramAddress) {
        super(code, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        throw new IllegalStateException("Can't execute dummy instruction");
    }

    @Override
    public String instCode() {
        return "DUMMY";
    }
}