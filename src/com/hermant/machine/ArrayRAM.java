package com.hermant.machine;

import java.util.Random;

public class ArrayRAM implements RandomAccessMemory{

    private RandomAccessMemory.Endianness endianness;
    private byte[] memory;

    ArrayRAM(RandomAccessMemory.Endianness endianness, int size){
        this.endianness = endianness;
        memory = new byte[size];
        Random random = new Random();
        for (int i = 0; i < memory.length; i++) {
            memory[i] = (byte)random.nextInt();
        }
    }

    @Override
    public Endianness getEndianness() {
        return endianness;
    }

    @Override
    public void setByte(int address, byte value) {
        memory[address] = value;
    }

    @Override
    public int getByte(int address) {
        return Byte.toUnsignedInt(memory[address]);
    }

}
