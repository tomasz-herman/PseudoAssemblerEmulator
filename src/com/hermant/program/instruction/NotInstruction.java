package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class NotInstruction extends Instruction {

    NotInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.NOT, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister());
        m.getRam().setInteger(ramAddress, ~m.getRam().getInteger(ramAddress));
        return true;
    }
}