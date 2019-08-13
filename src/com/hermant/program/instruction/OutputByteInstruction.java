package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class OutputByteInstruction extends OutputOperation implements MemoryOperation {

    OutputByteInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(Instruction.OUTPUT_BYTE, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        System.out.println(Byte.toUnsignedInt(m.getRam().getByte(ramAddress)));
        return true;
    }

    @Override
    public String instCode() {
        return "OUTPUT_BYTE";
    }
}