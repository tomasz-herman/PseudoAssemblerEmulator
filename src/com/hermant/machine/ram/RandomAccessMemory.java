package com.hermant.machine.ram;

public abstract class RandomAccessMemory {

    private Endianness endianness;

    RandomAccessMemory(Endianness endianness){
        this.endianness = endianness;
    }

    public abstract void setByte(int address, byte value);

    public abstract int getByte(int address);

    public void setInteger(int address, int value){ endianness.setInteger(this::setByte, address, value); }

    public int getInteger(int address){ return endianness.getInteger(this::getByte, address); }

    public void setFloat(int address, Float value){ endianness.setFloat(this::setByte, address, value); }

    public float getFloat(int address){ return endianness.getFloat(this::getByte, address); }

}
