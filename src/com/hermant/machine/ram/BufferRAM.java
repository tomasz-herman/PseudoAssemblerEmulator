package com.hermant.machine.ram;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class BufferRAM extends RandomAccessMemory {

    private ByteBuffer buffer;

    public BufferRAM(Endianness endianness, int size) {
        super(endianness);
        buffer = ByteBuffer.allocate(size).order(
                endianness == Endianness.LittleEndian ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN);
    }

    @Override
    public void setByte(int address, byte value) {
        buffer.put(address, value);
    }

    @Override
    public byte getByte(int address) {
        return buffer.get(address);
    }

    @Override
    public void setShort(int address, short value) {
        buffer.putShort(address, value);
    }

    @Override
    public short getShort(int address) {
        return buffer.getShort(address);
    }

    @Override
    public void setInteger(int address, int value) {
        buffer.putInt(address, value);
    }

    @Override
    public int getInteger(int address) {
        return buffer.getInt(address);
    }

    @Override
    public void setFloat(int address, float value) {
        buffer.putFloat(address, value);
    }

    @Override
    public float getFloat(int address) {
        return buffer.getFloat(address);
    }
}