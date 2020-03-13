package com.hermant.machine.memory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnsafeRAMTest {

    @Test
    void unsafeSetAndGetInteger() {
        UnsafeRAM ram = new UnsafeRAM(1024 * 1024);
        int[] addresses = {0x00000000, 0x00000004, 0x00000000C, 0x000000FF, 0x00000567, 0x00000EEE, 0x000000AA, 0x00000074};
        int[] values = {3423, 65555, 19299292, -4354, 0, 12, 11111111, -1};
        for (int i = 0, valuesLength = values.length; i < valuesLength; i++) {
            int value = values[i];
            int address = addresses[i];
            ram.setInteger(address, value);
        }
        for (int i = 0, valuesLength = values.length; i < valuesLength; i++) {
            int value = values[i];
            int address = addresses[i];
            assertEquals(value, ram.getInteger(address));
        }
        ram.free();
    }

    @Test
    void unsafeSetAndGetByte() {
        UnsafeRAM ram = new UnsafeRAM(1024 * 1024);
        int[] addresses = {0x00000000, 0x00000001, 0x000000003, 0x00000004, 0x00000002, 0x00000EEE, 0x000000AA, 0x00000074};
        byte[] values = {33, 65, 127, -128, 0, -0, -22, -1};
        for (int i = 0, valuesLength = values.length; i < valuesLength; i++) {
            byte value = values[i];
            int address = addresses[i];
            ram.setByte(address, value);
        }
        for (int i = 0, valuesLength = values.length; i < valuesLength; i++) {
            byte value = values[i];
            int address = addresses[i];
            assertEquals(value, ram.getByte(address));
        }
        ram.free();
    }

    @Test
    void unsafeSetAndGetFloat() {
        UnsafeRAM ram = new UnsafeRAM(1024 * 1024);
        int[] addresses = {0x00000000, 0x00000004, 0x00000000C, 0x000000FF, 0x00000567, 0x00000EEE, 0x000000AA, 0x00000074};
        float[] values = {Float.POSITIVE_INFINITY, 0.0f, Float.NaN, -1e22f, 2.43e-2f, 1.0f, Float.MIN_VALUE, Float.MAX_VALUE};
        for (int i = 0, valuesLength = values.length; i < valuesLength; i++) {
            float value = values[i];
            int address = addresses[i];
            ram.setFloat(address, value);
        }
        for (int i = 0, valuesLength = values.length; i < valuesLength; i++) {
            float value = values[i];
            int address = addresses[i];
            assertEquals(value, ram.getFloat(address));
        }
        ram.free();
    }

    @Test
    void unsafeSetAndGetShort() {
        UnsafeRAM ram = new UnsafeRAM(1024 * 1024);
        int[] addresses = {0x00000000, 0x00000002, 0x000000004, 0x00000008, 0x00000567, 0x00000EEE, 0x000000AA, 0x00000074};
        short[] values = {3423, 32767, -32768, -4354, 0, 12, 11111, -1};
        for (int i = 0, valuesLength = values.length; i < valuesLength; i++) {
            short value = values[i];
            int address = addresses[i];
            ram.setShort(address, value);
        }
        for (int i = 0, valuesLength = values.length; i < valuesLength; i++) {
            short value = values[i];
            int address = addresses[i];
            assertEquals(value, ram.getShort(address));
        }
        ram.free();
    }

}