package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class TestFloatInstruction extends Instruction {

    TestFloatInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.TEST_FLOAT, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister());
        compareFloat(m.getRam().getFloat(ramAddress), 0f, m.getFlagsRegister());
        return true;
    }
}