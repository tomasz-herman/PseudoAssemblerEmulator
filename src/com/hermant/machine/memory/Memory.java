package com.hermant.machine.memory;

public interface Memory {

    void setByte(int address, byte value);

    byte getByte(int address);

    void setShort(int address, short value);

    short getShort(int address);

    void setInteger(int address, int value);

    int getInteger(int address);

    void setFloat(int address, float value);

    float getFloat(int address);

    void free();
}
