package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class TestFloatInstruction extends Instruction implements MemoryOperation, FloatArithmetic {

    TestFloatInstruction(Byte reg1, Byte reg2, Short ramAddress) {
        super(Instruction.TEST_FLOAT, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        compareFloat(m.getRam().getFloat(ramAddress), 0f, m.getFlagsRegister());
        return true;
    }

    @Override
    public String instCode() {
        return "TEST_FLOAT";
    }
}