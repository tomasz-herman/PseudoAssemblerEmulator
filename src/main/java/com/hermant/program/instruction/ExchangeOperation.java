package com.hermant.program.instruction;

import com.hermant.machine.memory.RandomAccessMemory;
import com.hermant.machine.register.FloatingPointRegister;
import com.hermant.machine.register.GeneralPurposeRegister;

public interface ExchangeOperation {

    default void exchangeRegReg(GeneralPurposeRegister reg, int reg1, int reg2){
        int temp = reg.get(reg1);
        reg.set(reg1, reg.get(reg2));
        reg.set(reg2, temp);
    }

    default void exchangeRegMem(GeneralPurposeRegister reg, int reg1, RandomAccessMemory ram, int memAddr){
        int temp = reg.get(reg1);
        reg.set(reg1, ram.getInteger(memAddr));
        ram.setInteger(memAddr, temp);
    }

    default void exchangeFloatRegReg(FloatingPointRegister reg, int reg1, int reg2){
        float temp = reg.get(reg1);
        reg.set(reg1, reg.get(reg2));
        reg.set(reg2, temp);
    }

    default void exchangeFloatRegMem(FloatingPointRegister reg, int reg1, RandomAccessMemory ram, int memAddr){
        float temp = reg.get(reg1);
        reg.set(reg1, ram.getFloat(memAddr));
        ram.setFloat(memAddr, temp);
    }
}
