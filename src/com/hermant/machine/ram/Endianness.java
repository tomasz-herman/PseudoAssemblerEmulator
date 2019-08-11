package com.hermant.machine.ram;

public abstract class Endianness implements RandomAccessMemory{

    protected RandomAccessMemory ram;

    Endianness(RandomAccessMemory ram) {
        this.ram = ram;
    }

    public abstract void setInteger(int address, int value);

    public abstract int getInteger(int address);

    public void setFloat(int address, Float value){
        setInteger(address, Float.floatToIntBits(value));
    }

    public float getFloat(int address){
        return Float.intBitsToFloat(getInteger(address));
    }

    @Override
    public void setByte(int address, byte value) {
        ram.setByte(address, value);
    }

    @Override
    public int getByte(int address) {
        return ram.getByte(address);
    }
}
