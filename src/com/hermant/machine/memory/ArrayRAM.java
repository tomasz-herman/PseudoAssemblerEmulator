package com.hermant.machine.memory;

import java.util.Random;

public class ArrayRAM extends RandomAccessMemory{

    private byte[] memory;

    public ArrayRAM(Endianness endianness, int size){
        super(endianness);
        memory = new byte[size];
        Random random = new Random();
        for (int i = 0; i < memory.length; i++) {
            memory[i] = (byte)random.nextInt();
        }
    }

    @Override
    public void setByte(int address, byte value) {
        memory[address] = value;
    }

    @Override
    public byte getByte(int address) {
        return memory[address];
    }

}
