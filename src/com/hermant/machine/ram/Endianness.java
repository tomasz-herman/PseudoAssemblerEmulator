package com.hermant.machine.ram;

import java.util.function.BiConsumer;
import java.util.function.Function;

public interface Endianness{

    void setInteger(BiConsumer<Integer, Byte> setByte, int address, int value);

    int getInteger(Function<Integer, Integer> getByte, int address);

    default void setFloat(BiConsumer<Integer, Byte> setByte, int address, Float value){
        setInteger(setByte, address, Float.floatToIntBits(value));
    }

    default float getFloat(Function<Integer, Integer> getByte, int address){
        return Float.intBitsToFloat(getInteger(getByte, address));
    }

}
