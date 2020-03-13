package com.hermant.machine.memory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayRAMTest {

    @Test
    void setAndGetByte() {
        ArrayRAM ram = new ArrayRAM(Endianness.LittleEndian, 16);
        byte[] values = {-1, 0, -1, 15, 16, 77, 127, -128, 34, -45, 56, 87, -33, 1, 2, 0};
        for (int i = 0; i < 16; i++) {
            ram.setByte(i, values[i]);
        }
        for (int i = 0; i < 16; i++) {
            assertEquals(ram.getByte(i), values[i]);
        }
    }

    @Test
    void outOfBounds() {
        ArrayRAM ram = new ArrayRAM(Endianness.LittleEndian, 16);
        assertThrows(IndexOutOfBoundsException.class, () -> ram.getByte(16));
    }

}