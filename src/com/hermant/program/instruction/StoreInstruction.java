package com.hermant.program.instruction;

import com.hermant.machine.*;

public class StoreInstruction extends Instruction implements MemoryOperation {

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        m.getRam().setInteger(ramAddress, m.getRegister().getInteger(reg1));
        return true;
    }

    @Override
    public byte code() {
        return STORE;
    }

    @Override
    public int instLength() {
        return 4;
    }

    @Override
    public String instCode() {
        return "STORE";
    }
}
