package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class OutputCharInstruction extends OutputOperation {

    OutputCharInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.OUTPUT_CHAR, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister());
        System.out.println((char)m.getRam().getByte(ramAddress));
        return true;
    }

    @Override
    public String instCode() {
        return "OUTPUT_CHAR";
    }
}