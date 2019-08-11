package com.hermant.machine.ram;

import java.util.function.BiConsumer;
import java.util.function.Function;

public class MiddleEndian implements Endianness {

    @Override
    public void setInteger(BiConsumer<Integer, Byte> setByte, int address, int value) {
        setByte.accept(address++, (byte)((value & 0x00ff0000)>>16));
        setByte.accept(address++, (byte)((value & 0xff000000)>>24));
        setByte.accept(address++, (byte)((value & 0x000000ff)));
        setByte.accept(address, (byte)((value & 0x0000ff00)>>8));
    }

    @Override
    public int getInteger(Function<Integer, Integer> getByte, int address) {
        return (getByte.apply(address++)<<16)|(getByte.apply(address++)<<24)|(getByte.apply(address++))|(getByte.apply(address)<<8);
    }
}
