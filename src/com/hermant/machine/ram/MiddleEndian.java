package com.hermant.machine.ram;

public class MiddleEndian extends Endianness {

    public MiddleEndian(RandomAccessMemory ram) {
        super(ram);
    }

    public void setInteger(int address, int value) {
        ram.setByte(address++, (byte)((value & 0x00ff0000)>>16));
        ram.setByte(address++, (byte)((value & 0xff000000)>>24));
        ram.setByte(address++, (byte)((value & 0x000000ff)));
        ram.setByte(address, (byte)((value & 0x0000ff00)>>8));
    }

    public int getInteger(int address){
        return (ram.getByte(address++)<<16)|(ram.getByte(address++)<<24)|(ram.getByte(address++))|(ram.getByte(address)<<8);
    }
}
