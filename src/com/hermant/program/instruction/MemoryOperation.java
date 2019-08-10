package com.hermant.program.instruction;

import com.hermant.machine.Register;

public interface MemoryOperation {

    default int getMemoryAddress(Register reg, int address, int addressOffset){ return reg.getInteger(address) + addressOffset; }
}
