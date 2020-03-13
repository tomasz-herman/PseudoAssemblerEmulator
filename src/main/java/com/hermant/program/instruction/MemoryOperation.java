package com.hermant.program.instruction;

import com.hermant.machine.register.GeneralPurposeRegister;

public interface MemoryOperation {

    default int getMemoryAddress(GeneralPurposeRegister reg, int address, short addressOffset){
        final int indirect = reg.get(address);
        final int direct = indirect + addressOffset;
        return indirect & 0xffff0000 | (direct & 0xffff);
    }
}
