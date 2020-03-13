package com.hermant.machine.memory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomAccessMemoryTest {

    @Test
    void littleEndianness() {
        ArrayRAM ram = new ArrayRAM(Endianness.LittleEndian, 16);
        byte[] values = {0, 0, 0, 2, 1, 0, 0, 0, -1, -1, 0, -1, 0, 15, 0, 0};
        int[] intValues = {
                0b00000010_00000000_00000000_00000000,
                0b00000000_00000000_00000000_00000001,
                0b11111111_00000000_11111111_11111111,
                0b00000000_00000000_00001111_00000000
        };
        short[] shortValues = {
                0b00000000_00000000, 0b00000010_00000000,
                0b00000000_00000001, 0b00000000_00000000,
                (short)0b11111111_11111111, (short)0b11111111_00000000,
                0b00001111_00000000, 0b00000000_00000000
        };
        for (int i = 0; i < 16; i++) {
            ram.setByte(i, values[i]);
        }
        for (int i = 0; i < 16; i+=4) {
            assertEquals(intValues[i>>2], ram.getInteger(i));
        }
        for (int i = 0; i < 16; i+=2) {
            assertEquals(shortValues[i>>1], ram.getShort(i));
        }
    }

    @Test
    void bigEndianness() {
        ArrayRAM ram = new ArrayRAM(Endianness.BigEndian, 16);
        byte[] values = {0, 0, 0, 2, 1, 0, 0, 0, -1, -1, 0, -1, 0, 15, 0, 0};
        int[] intValues = {
                0b00000000_00000000_00000000_00000010,
                0b00000001_00000000_00000000_00000000,
                0b11111111_11111111_00000000_11111111,
                0b00000000_00001111_00000000_00000000
        };
        short[] shortValues = {
                0b00000000_00000000, 0b00000000_00000010,
                0b00000001_00000000, 0b00000000_00000000,
                (short)0b11111111_11111111, 0b00000000_11111111,
                0b00000000_00001111, 0b00000000_00000000
        };
        for (int i = 0; i < 16; i++) {
            ram.setByte(i, values[i]);
        }
        for (int i = 0; i < 16; i+=4) {
            assertEquals(ram.getInteger(i), intValues[i>>2]);
        }
        for (int i = 0; i < 16; i+=2) {
            assertEquals(shortValues[i>>1], ram.getShort(i));
        }
    }

    @Test
    void intToFloat() {
        ArrayRAM ramLittle = new ArrayRAM(Endianness.LittleEndian, 64);
        ArrayRAM ramBig = new ArrayRAM(Endianness.BigEndian, 64);
        int[] values = {0x934FAB33, 0, -1,
                Float.floatToIntBits(Float.NaN),
                Float.floatToIntBits(Float.POSITIVE_INFINITY),
                Float.floatToIntBits(Float.NEGATIVE_INFINITY),
                Float.floatToIntBits(Float.MAX_VALUE),
                Float.floatToIntBits(Float.MIN_NORMAL)
        };
        for (int i = 0; i < values.length; i++) {
            ramBig.setInteger(i << 2, values[i]);
            ramLittle.setInteger(i << 2, values[i]);
        }
        for (int i = 0; i < values.length; i++) {
            assertEquals(ramBig.getFloat(i << 2), Float.intBitsToFloat(values[i]));
            assertEquals(ramLittle.getFloat(i << 2), Float.intBitsToFloat(values[i]));
        }
    }

    @Test
    void floatToInt() {
        ArrayRAM ramLittle = new ArrayRAM(Endianness.LittleEndian, 64);
        ArrayRAM ramBig = new ArrayRAM(Endianness.BigEndian, 64);
        float[] values = {0x934FAB33, 0f, -1f, 1f, 3.1415f,
                Float.NaN,
                Float.POSITIVE_INFINITY,
                Float.NEGATIVE_INFINITY,
                Float.MAX_VALUE,
                Float.MIN_NORMAL
        };
        for (int i = 0; i < values.length; i++) {
            ramBig.setFloat(i << 2, values[i]);
            ramLittle.setFloat(i << 2, values[i]);
        }
        for (int i = 0; i < values.length; i++) {
            assertEquals(ramBig.getInteger(i << 2), Float.floatToRawIntBits(values[i]));
            assertEquals(ramLittle.getInteger(i << 2), Float.floatToRawIntBits(values[i]));
        }
    }

}