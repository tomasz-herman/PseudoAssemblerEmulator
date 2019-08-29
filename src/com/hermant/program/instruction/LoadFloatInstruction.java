package com.hermant.program.instruction;

import com.hermant.machine.*;

public class LoadFloatInstruction extends Instruction implements MemoryOperation {

    LoadFloatInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(LOAD_FLOAT, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        m.getFPR().setInteger(reg1, m.getRam().getInteger(ramAddress));
        return true;
    }

    @Override
    public int instLength() {
        return 4;
    }

    @Override
    public String instCode() {
        return "LOAD_FLOAT";
    }
}
