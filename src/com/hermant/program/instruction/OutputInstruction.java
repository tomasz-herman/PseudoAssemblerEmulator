package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class OutputInstruction extends OutputOperation implements MemoryOperation {

    OutputInstruction(Byte reg1, Byte reg2, Short ramAddress) {
        super(Instruction.OUTPUT, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        System.out.println(Integer.toUnsignedString(m.getRam().getInteger(ramAddress)));
        return true;
    }

    @Override
    public String instCode() {
        return "OUTPUT";
    }
}