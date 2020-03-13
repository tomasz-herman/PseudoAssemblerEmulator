package com.hermant.machine.memory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashMapRAMTest {

    @Test
    void setAndGetByte() {
        HashMapRAM ram = new HashMapRAM(Endianness.LittleEndian);
        byte[] values = {-1, 0, -1, 15, 16, 77, 127, -128, 34, -45, 56, 87, -33, 1, 2, 0};
        int[] addresses = {0, -1, 232, 534564, -45345345, 345345345, 222266660, -453454354, 222222222, 1, 2, 3, 34, 7689, 1000, 1024};
        for (int i = 0; i < addresses.length; i++) {
            ram.setByte(addresses[i], values[i]);
        }
        for (int i = 0; i < addresses.length; i++) {
            assertEquals(ram.getByte(addresses[i]), values[i]);
        }
    }

}