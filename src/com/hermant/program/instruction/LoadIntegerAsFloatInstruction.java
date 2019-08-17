package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class LoadIntegerAsFloatInstruction extends Instruction implements MemoryOperation{

    LoadIntegerAsFloatInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(Instruction.LOAD_INTEGER_AS_FLOAT, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        m.getFPR().setFloat(reg1, m.getRam().getInteger(ramAddress));
        return true;
    }

    @Override
    public int instLength() {
        return 4;
    }

    @Override
    public String instCode() {
        return "LOAD_INTEGER_AS_FLOAT";
    }
}