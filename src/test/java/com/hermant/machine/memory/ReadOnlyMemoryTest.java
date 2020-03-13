package com.hermant.machine.memory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReadOnlyMemoryTest {

    @Test
    void setByte() {
        ReadOnlyMemory rom = new ReadOnlyMemory(new ArrayRAM(Endianness.LittleEndian, 64));
        assertThrows(UnsupportedOperationException.class, () -> rom.setByte(0, (byte) 0));
    }

    @Test
    void setShort() {
        ReadOnlyMemory rom = new ReadOnlyMemory(new ArrayRAM(Endianness.LittleEndian, 64));
        assertThrows(UnsupportedOperationException.class, () -> rom.setShort(0, (short) 0));
    }

    @Test
    void setInteger() {
        ReadOnlyMemory rom = new ReadOnlyMemory(new ArrayRAM(Endianness.LittleEndian, 64));
        assertThrows(UnsupportedOperationException.class, () -> rom.setInteger(0, 0));
    }

    @Test
    void setFloat() {
        ReadOnlyMemory rom = new ReadOnlyMemory(new ArrayRAM(Endianness.LittleEndian, 64));
        assertThrows(UnsupportedOperationException.class, () -> rom.setFloat(0, 0f));
    }

    @Test
    void getByte() {
        RandomAccessMemory ram = new ArrayRAM(Endianness.LittleEndian, 64);
        for (int i = 0; i < 64; i++) {
            ram.setByte(i, (byte)(i * 2));
        }
        ReadOnlyMemory rom = new ReadOnlyMemory(ram);
        for (int i = 0; i < 64; i++) {
            assertEquals(i * 2, rom.getByte(i));
        }
    }

    @Test
    void getShort() {
        RandomAccessMemory ram = new ArrayRAM(Endianness.LittleEndian, 128);
        for (int i = 0; i < 64; i++) {
            ram.setShort(i * 2, (short)(i * 31 + 13));
        }
        ReadOnlyMemory rom = new ReadOnlyMemory(ram);
        for (int i = 0; i < 64; i++) {
            assertEquals(i * 31 + 13, rom.getShort(i * 2));
        }
    }

    @Test
    void getInteger() {
        RandomAccessMemory ram = new ArrayRAM(Endianness.LittleEndian, 128);
        for (int i = 0; i < 32; i++) {
            ram.setInteger(i * 4, i * 321 - 13 * i * i);
        }
        ReadOnlyMemory rom = new ReadOnlyMemory(ram);
        for (int i = 0; i < 32; i++) {
            assertEquals(i * 321 - 13 * i * i, rom.getInteger(i * 4));
        }
    }

    @Test
    void getFloat() {
        RandomAccessMemory ram = new ArrayRAM(Endianness.LittleEndian, 128);
        for (int i = 0; i < 32; i++) {
            ram.setFloat(i * 4, i * i * i * 3.14f + 1e4f);
        }
        ReadOnlyMemory rom = new ReadOnlyMemory(ram);
        for (int i = 0; i < 32; i++) {
            assertEquals(i * i * i * 3.14f + 1e4f, rom.getFloat(i * 4));
        }
    }
}