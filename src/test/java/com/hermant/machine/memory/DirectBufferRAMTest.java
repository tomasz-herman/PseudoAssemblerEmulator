package com.hermant.machine.memory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectBufferRAMTest {

    @Test
    void setAndGetByte(){
        DirectBufferRAM ramLittle = new DirectBufferRAM(Endianness.LittleEndian, 16);
        DirectBufferRAM ramBig = new DirectBufferRAM(Endianness.BigEndian, 16);

        for (int i = 0; i < 16; i++) {
            ramLittle.setByte(i, (byte)(i - 8));
            ramBig.setByte(i, (byte)(i - 8));
        }
        for (int i = 0; i < 16; i++) {
            assertEquals(i - 8, ramLittle.getByte(i));
            assertEquals(i - 8, ramBig.getByte(i));
        }
    }

    @Test
    void setAndGetShort(){
        DirectBufferRAM ramLittle = new DirectBufferRAM(Endianness.LittleEndian, 16);
        DirectBufferRAM ramBig = new DirectBufferRAM(Endianness.BigEndian, 16);

        for (int i = 0; i < 16; i+=2) {
            ramLittle.setShort(i, (short)(i - 8 * i * i + 1));
            ramBig.setShort(i, (short)(i - 8 * i * i + 1));
        }
        for (int i = 0; i < 16; i+=2) {
            assertEquals(i - 8 * i * i + 1, ramLittle.getShort(i));
            assertEquals(i - 8 * i * i + 1, ramBig.getShort(i));
        }
    }

    @Test
    void setAndGetInt(){
        DirectBufferRAM ramLittle = new DirectBufferRAM(Endianness.LittleEndian, 16);
        DirectBufferRAM ramBig = new DirectBufferRAM(Endianness.BigEndian, 16);

        for (int i = 0; i < 16; i+=4) {
            int value = (i << 16) - 8 * i * i * i * i + 1666003;
            ramLittle.setInteger(i, value);
            ramBig.setInteger(i, value);
        }
        for (int i = 0; i < 16; i+=4) {
            int value = (i << 16) - 8 * i * i * i * i + 1666003;
            assertEquals(value, ramLittle.getInteger(i));
            assertEquals(value, ramBig.getInteger(i));
        }
    }

    @Test
    void setAndGetFloat(){
        DirectBufferRAM ramLittle = new DirectBufferRAM(Endianness.LittleEndian, 16);
        DirectBufferRAM ramBig = new DirectBufferRAM(Endianness.BigEndian, 16);

        for (int i = 0; i < 16; i+=4) {
            float value = (float)Math.sin(i) * 2 + 1.0f;
            ramLittle.setFloat(i, value);
            ramBig.setFloat(i, value);
        }
        for (int i = 0; i < 16; i+=4) {
            float value = (float)Math.sin(i) * 2 + 1.0f;
            assertEquals(value, ramLittle.getFloat(i));
            assertEquals(value, ramBig.getFloat(i));
        }
    }
}