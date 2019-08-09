package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class RandomInstruction extends Instruction {

    RandomInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.RANDOM, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister());
        m.getRam().setInteger(ramAddress, m.getRam().random.nextInt());
        return true;
    }
}