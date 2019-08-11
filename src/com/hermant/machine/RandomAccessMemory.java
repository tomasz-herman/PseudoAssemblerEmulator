package com.hermant.machine;

public interface RandomAccessMemory {


    enum Endianness{
        BigEndian, LittleEndian, MiddleEndian

    }
    
    Endianness getEndianness();

    void setByte(int address, byte value);

    int getByte(int address);

    default void setInteger(int address, int value) {
        switch(getEndianness()){
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

    default void setFloat(int address, Float value){
        setInteger(address, Float.floatToIntBits(value));
    }

    default int getInteger(int address){
        switch (getEndianness()){
            case BigEndian ->
                    { return (getByte(address++)<<24)|(getByte(address++)<<16)|(getByte(address++)<<8)|(getByte(address));}
            case LittleEndian ->
                    { return (getByte(address++)|(getByte(address++)<<8)|(getByte(address++)<<16)|(getByte(address)<<24));}
            case MiddleEndian ->
                    { return (getByte(address++)<<16)|(getByte(address++)<<24)|(getByte(address++))|(getByte(address)<<8);}
        }
        return 69420;
    }

    default float getFloat(int address){
        return Float.intBitsToFloat(getInteger(address));
    }

}
