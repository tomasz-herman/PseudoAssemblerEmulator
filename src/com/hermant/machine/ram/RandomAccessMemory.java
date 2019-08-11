package com.hermant.machine.ram;

public interface RandomAccessMemory {

    void setByte(int address, byte value);

    int getByte(int address);

    default void setInteger(int address, int value){throw new IllegalStateException("Bytes interpretation not set");}

    default int getInteger(int address){throw new IllegalStateException("Bytes interpretation not set");}

    default void setFloat(int address, Float value){throw new IllegalStateException("Bytes interpretation not set");}

    default float getFloat(int address){throw new IllegalStateException("Bytes interpretation not set");}

}
