package com.hermant.program.instruction;

import com.hermant.machine.register.FlagsRegister;

import java.util.function.BiFunction;

public interface IntegerArithmetic {

    default void compare(int a, int b, FlagsRegister flags, BiFunction<Long, Long, Long> bi){
        long signed = bi.apply((long)a, (long)b);
        long unsigned = bi.apply(Integer.toUnsignedLong(a), Integer.toUnsignedLong(b));
        if((signed & 0x80000000) != 0)flags.setSignFlag();
        else flags.resetSignFlag();
        if(signed == 0)flags.setZeroFlag();
        else flags.resetZeroFlag();
        if(signed < Integer.MIN_VALUE || signed > Integer.MAX_VALUE) flags.setOverflowFlag();
        else flags.resetOverflowFlag();
        if((unsigned & 0x100000000L) != 0)flags.setCarryFlag();
        else flags.resetCarryFlag();
        if((Integer.bitCount((int)signed)&0x1)==0)flags.setParityFlag();
        else flags.resetParityFlag();
    }
}
