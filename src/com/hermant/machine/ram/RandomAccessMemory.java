package com.hermant.machine.ram;

public abstract class RandomAccessMemory {

    private Endianness endianness;

    RandomAccessMemory(Endianness endianness){
        this.endianness = endianness;
    }

    public abstract void setByte(int address, byte value);

    public abstract byte getByte(int address);

    public final void setShort(int address, short value){ endianness.setShort(this::setByte, address, value); }

    public final short getShort(int address){ return endianness.getShort(this::getByte, address); }

    public final void setInteger(int address, int value){ endianness.setInteger(this::setByte, address, value); }

    public final int getInteger(int address){ return endianness.getInteger(this::getByte, address); }

    public final void setFloat(int address, Float value){ setInteger(address, Float.floatToIntBits(value)); }

    public final float getFloat(int address){ return Float.intBitsToFloat(getInteger(address)); }

}
