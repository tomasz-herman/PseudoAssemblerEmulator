package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class StoreByteInstruction extends Instruction implements MemoryOperation {

    StoreByteInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(STORE_BYTE, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        m.getRam().setByte(ramAddress, (byte)m.getRegister().getInteger(reg1));
        return true;
    }

    @Override
    public int instLength() {
        return 4;
    }

    @Override
    public String instCode() {
        return "STORE_BYTE";
    }
}