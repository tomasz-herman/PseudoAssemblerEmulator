package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class OutputFloatInstruction extends OutputOperation implements MemoryOperation {

    OutputFloatInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(Instruction.OUTPUT_FLOAT, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        System.out.print(m.getRam().getFloat(ramAddress));
        if(debug) System.out.println();
        return true;
    }

    @Override
    public String instCode() {
        return "OUTPUT_FLOAT";
    }
}