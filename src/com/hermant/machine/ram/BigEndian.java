package com.hermant.machine.ram;

public class BigEndian extends Endianness {

    public BigEndian(RandomAccessMemory ram) {
        super(ram);
    }

    public void setInteger(int address, int value) {
        ram.setByte(address++, (byte)((value & 0xff000000)>>24));
        ram.setByte(address++, (byte)((value & 0x00ff0000)>>16));
        ram.setByte(address++, (byte)((value & 0x0000ff00)>>8));
        ram.setByte(address, (byte)((value & 0x000000ff)));
    }

    public int getInteger(int address){
        return (ram.getByte(address++)<<24)|(ram.getByte(address++)<<16)|(ram.getByte(address++)<<8)|(ram.getByte(address));
    }
}
