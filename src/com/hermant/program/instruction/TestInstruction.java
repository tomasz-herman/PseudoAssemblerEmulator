package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class TestInstruction extends Instruction {

    TestInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.TEST, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister());
        setFlagsAfterLogicalOp(m.getRegister().getInteger(reg1) & m.getRam().getInteger(ramAddress), m.getFlagsRegister());
        return true;
    }
}