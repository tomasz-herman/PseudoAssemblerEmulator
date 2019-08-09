package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class OutputSignedInstruction extends Instruction {

    OutputSignedInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.OUTPUT_SIGNED, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister());
        System.out.println(m.getRam().getInteger(ramAddress));
        return true;
    }
}