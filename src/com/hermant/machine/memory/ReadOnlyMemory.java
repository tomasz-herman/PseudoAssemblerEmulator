package com.hermant.machine.memory;

public final class ReadOnlyMemory implements Memory{

    private RandomAccessMemory ram;

    public ReadOnlyMemory(RandomAccessMemory ram){
        this.ram = ram;
    }

    @Override
    public void setByte(int address, byte value) {
        throw new UnsupportedOperationException("This memory is read only.");
    }

    @Override
    public byte getByte(int address) {
        return ram.getByte(address);
    }

    @Override
    public void setShort(int address, short value) {
        throw new UnsupportedOperationException("This memory is read only.");
    }

    @Override
    public short getShort(int address) {
        return ram.getShort(address);
    }

    @Override
    public void setInteger(int address, int value) {
        throw new UnsupportedOperationException("This memory is read only.");
    }

    @Override
    public int getInteger(int address) {
        return ram.getInteger(address);
    }

    @Override
    public void setFloat(int address, float value) {
        throw new UnsupportedOperationException("This memory is read only.");
    }

    @Override
    public float getFloat(int address) {
        return ram.getFloat(address);
    }

    @Override
    public void free() {
        ram.free();
    }
}
