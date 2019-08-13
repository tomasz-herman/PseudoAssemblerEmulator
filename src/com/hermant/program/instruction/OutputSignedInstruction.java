package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class OutputSignedInstruction extends OutputOperation implements MemoryOperation {

    OutputSignedInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(Instruction.OUTPUT_SIGNED, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        System.out.println(m.getRam().getInteger(ramAddress));
        return true;
    }

    @Override
    public String instCode() {
        return "OUTPUT_SIGNED";
    }
}