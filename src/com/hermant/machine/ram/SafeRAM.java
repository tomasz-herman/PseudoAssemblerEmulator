package com.hermant.machine.ram;

public class SafeRAM extends UnsafeRAM {

    private int size;

    public SafeRAM(int size) {
        super(size);
        this.size = size;
    }

    @Override
    public void setByte(int address, byte value) {
        if(address + 1 > size || address < 0) throw new RuntimeException("Illegal access to memory");
        super.setByte(address, value);
    }

    @Override
    public byte getByte(int address) {
        if(address + 1 > size || address < 0) throw new RuntimeException("Illegal access to memory");
        return super.getByte(address);
    }

    @Override
    public void setInteger(int address, int value) {
        if(address + 4 > size || address < 0) throw new RuntimeException("Illegal access to memory");
        super.setInteger(address, value);
    }

    @Override
    public int getInteger(int address) {
        if(address + 4 > size || address < 0) throw new RuntimeException("Illegal access to memory");
        return super.getInteger(address);
    }

    @Override
    public void setShort(int address, short value) {
        if(address + 2 > size || address < 0) throw new RuntimeException("Illegal access to memory");
        super.setShort(address, value);
    }

    @Override
    public short getShort(int address) {
        if(address + 2 > size || address < 0) throw new RuntimeException("Illegal access to memory");
        return super.getShort(address);
    }

    @Override
    public void setFloat(int address, float value) {
        if(address + 4 > size || address < 0) throw new RuntimeException("Illegal access to memory");
        super.setFloat(address, value);
    }

    @Override
    public float getFloat(int address) {
        if(address + 4 > size || address < 0) throw new RuntimeException("Illegal access to memory");
        return super.getFloat(address);
    }
}
