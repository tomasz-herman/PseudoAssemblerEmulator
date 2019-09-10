package com.hermant.program.instruction;

import com.hermant.machine.register.GeneralPurposeRegister;

public interface MemoryOperation {

    default int getMemoryAddress(GeneralPurposeRegister reg, int address, short addressOffset){
        int indirect = reg.get(address);
        short direct = (short)(indirect + addressOffset);
        return indirect & 0xffff0000 | Short.toUnsignedInt(direct);
    }
}
