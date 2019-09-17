package com.hermant.machine.memory;

public abstract class RandomAccessMemory implements Memory{

    private Endianness endianness;

    RandomAccessMemory(Endianness endianness){
        this.endianness = endianness;
    }

    public abstract void setByte(int address, byte value);

    public abstract byte getByte(int address);

    public void setShort(int address, short value){ endianness.setShort(this::setByte, address, value); }

    public short getShort(int address){ return endianness.getShort(this::getByte, address); }

    public void setInteger(int address, int value){ endianness.setInteger(this::setByte, address, value); }

    public int getInteger(int address){ return endianness.getInteger(this::getByte, address); }

    public void setFloat(int address, float value){ setInteger(address, Float.floatToIntBits(value)); }

    public float getFloat(int address){ return Float.intBitsToFloat(getInteger(address)); }

    public void free(){}

}
