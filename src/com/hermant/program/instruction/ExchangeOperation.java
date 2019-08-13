package com.hermant.program.instruction;

import com.hermant.machine.ram.RandomAccessMemory;
import com.hermant.machine.register.Register;

public interface ExchangeOperation extends MemoryOperation{

    default void exchangeRegReg(Register reg, int reg1, int reg2){
        int temp = reg.getInteger(reg1);
        reg.setInteger(reg1, reg.getInteger(reg2));
        reg.setInteger(reg2, temp);
    }

    default void exchangeRegMem(Register reg, int reg1,  RandomAccessMemory ram, int memAddr){
        int temp = reg.getInteger(reg1);
        reg.setInteger(reg1, ram.getInteger(memAddr));
        ram.setInteger(memAddr, temp);
    }
}
