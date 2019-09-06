package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class OutputSignedInstruction extends Instruction implements MemoryOperation, OutputOperation {

    OutputSignedInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(OUTPUT_SIGNED, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        System.out.print(m.getRam().getInteger(ramAddress));
        if(debug) System.out.println();
        return true;
    }

    @Override
    public int instLength() {
        return 4;
    }

    @Override
    public String instCode() {
        return "OUTPUT_SIGNED";
    }
}