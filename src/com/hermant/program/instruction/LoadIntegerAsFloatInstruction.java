package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class LoadIntegerAsFloatInstruction extends Instruction {

    LoadIntegerAsFloatInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.LOAD_INTEGER_AS_FLOAT, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister());
        m.getFPR().setFloat(reg1, m.getRam().getInteger(ramAddress));
        return true;
    }
}