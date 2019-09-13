package com.hermant.machine.ram;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.nio.ByteOrder;
import java.util.Random;

/**
 * Highly experimental
 * This method of simulating ram is superior in speed at the cost of random core dumps when accessing unallocated
 * memory. It doesn't allow to set Endianness. Internal machine's endianness will be used instead. Should be only
 * used if user knows what he is doing.
 */
public class UnsafeRAM extends RandomAccessMemory{

    private Unsafe unsafe;
    private long offset;

    /**
     * This method of simulating ram is superior in speed at the cost of random core dumps when accessing unallocated
     * memory. It doesn't allow to set Endianness. Native machine's endianness will be used instead. Should be only
     * used if user knows what he is doing.
     * @param size memory to allocate
     */
    public UnsafeRAM(int size) {
        super(ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN ? Endianness.BigEndian : Endianness.LittleEndian);
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        assert unsafe != null;
        offset = unsafe.allocateMemory(size);
        Random random = new Random();
        for (int i = 0; i < size; ) {
            int rand = random.nextInt();
            for (int j = 0; j < 4 & i < size ; j++, i++) {
                setByte(i, (byte)rand);
                rand >>= 8;
            }
        }
    }

    @Override
    public void setByte(int address, byte value) {
        unsafe.putByte(offset + address, value);
    }

    @Override
    public byte getByte(int address) {
        return unsafe.getByte(offset + address);
    }

    @Override
    public void setInteger(int address, int value) {
        unsafe.putInt(offset + address, value);
    }

    @Override
    public int getInteger(int address) {
        return unsafe.getInt(offset + address);
    }

    @Override
    public void setShort(int address, short value) {
        unsafe.putShort(offset + address, value);
    }

    @Override
    public short getShort(int address) {
        return unsafe.getShort(offset + address);
    }

    @Override
    public void setFloat(int address, float value) {
        unsafe.putFloat(offset + address, value);
    }

    @Override
    public float getFloat(int address) {
        return unsafe.getFloat(offset + address);
    }

    @Override
    public void free() {
        unsafe.freeMemory(offset);
    }
}
