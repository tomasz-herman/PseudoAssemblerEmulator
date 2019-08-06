package com.hermant.machine;

import java.util.HashMap;
import java.util.Random;

public class RandomAccessMemory {

    public enum Endianness{
        BigEndian, LittleEndian, MiddleEndian
    }

    public Endianness endianness;
    public Random random;
    private HashMap<Integer, Byte> memory;

    RandomAccessMemory(Endianness endianness){
        this.endianness = endianness;
        random = new Random();
        memory = new HashMap<>();
    }

    public void setByte(int address, byte value){
        memory.put(address, value);
    }

    public void setInteger(int address, int value){
        switch(endianness){
            case BigEndian -> {
                setByte(address++, (byte)((value & 0xff000000)>>24));
                setByte(address++, (byte)((value & 0x00ff0000)>>16));
                setByte(address++, (byte)((value & 0x0000ff00)>>8));
                setByte(address, (byte)((value & 0x000000ff)));
            }
            case LittleEndian -> {
                setByte(address++, (byte)((value & 0x000000ff)));
                setByte(address++, (byte)((value & 0x0000ff00)>>8));
                setByte(address++, (byte)((value & 0x00ff0000)>>16));
                setByte(address, (byte)((value & 0xff000000)>>24));
            }
            case MiddleEndian -> {
                setByte(address++, (byte)((value & 0x00ff0000)>>16));
                setByte(address++, (byte)((value & 0xff000000)>>24));
                setByte(address++, (byte)((value & 0x000000ff)));
                setByte(address, (byte)((value & 0x0000ff00)>>8));
            }
        }

    }

    public void setFloat(int address, Float value){
        setInteger(address, Float.floatToIntBits(value));
    }

    public int getByte(int address){
        if (!memory.containsKey(address)) memory.put(address, (byte) random.nextInt());
        return Byte.toUnsignedInt(memory.get(address));
    }

    public int getInteger(int address){
        switch (endianness){
            case BigEndian ->
                    { return (getByte(address++)<<24)|(getByte(address++)<<16)|(getByte(address++)<<8)|(getByte(address));}
            case LittleEndian ->
                    { return (getByte(address++)|(getByte(address++)<<8)|(getByte(address++)<<16)|(getByte(address)<<24));}
            case MiddleEndian ->
                    { return (getByte(address++)<<16)|(getByte(address++)<<24)|(getByte(address++))|(getByte(address)<<8);}
        }
        return 69420;
    }

    public float getFloat(int address){
        return Float.intBitsToFloat(getInteger(address));
    }

}
