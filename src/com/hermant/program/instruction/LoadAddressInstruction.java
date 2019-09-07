package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class LoadAddressInstruction extends Instruction implements MemoryOperation {

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        m.getRegister().setInteger(reg1, ramAddress);
        return true;
    }

    @Override
    public byte code() {
        return LOAD_ADDRESS;
    }

    @Override
    public int instLength() {
        return 4;
    }

    @Override
    public String instCode() {
        return "LOAD_ADDRESS";
    }
}
