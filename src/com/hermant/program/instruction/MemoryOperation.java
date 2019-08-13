package com.hermant.program.instruction;

import com.hermant.machine.register.Register;

public interface MemoryOperation {

    default int getMemoryAddress(Register reg, int address, short addressOffset){
        int indirect = reg.getInteger(address);
        short direct = (short)(indirect + addressOffset);
        return indirect & 0xffff0000 | Short.toUnsignedInt(direct);
    }
}
