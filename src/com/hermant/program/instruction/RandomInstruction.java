package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class RandomInstruction extends Instruction implements MemoryOperation {

    RandomInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(Instruction.RANDOM, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        m.getRam().setInteger(ramAddress, m.getRng().getNext());
        return true;
    }

    @Override
    public int instLength() {
        return 4;
    }

    @Override
    public String instCode() {
        return "RANDOM";
    }
}