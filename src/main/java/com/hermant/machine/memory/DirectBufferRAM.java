package com.hermant.machine.memory;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Random;

public class DirectBufferRAM extends RandomAccessMemory {

    private ByteBuffer buffer;

    public DirectBufferRAM(Endianness endianness, int size) {
        super(endianness);
        buffer = ByteBuffer.allocateDirect(size).order(
                endianness == Endianness.LittleEndian ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN);
        Random random = new Random();
        for (int i = 0; i < size; ) {
            int rand = random.nextInt();
            for (int j = 0; j < 4 & i < size ; j++, i++) {
                buffer.put(i, (byte)rand);
                rand >>= 8;
            }
        }
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
