package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class OutputInstruction extends Instruction implements MemoryOperation, OutputOperation {

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        System.out.print(Integer.toUnsignedString(m.getRam().getInteger(ramAddress)));
        if(debug) System.out.println();
        return true;
    }

    @Override
    public byte code() {
        return OUTPUT;
    }

    @Override
    public int instLength() {
        return 4;
    }

    @Override
    public String instCode() {
        return "OUTPUT";
    }
}