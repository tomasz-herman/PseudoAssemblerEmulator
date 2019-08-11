package com.hermant.machine;

import java.util.HashMap;
import java.util.Random;

public class HashMapRAM implements RandomAccessMemory{

    private RandomAccessMemory.Endianness endianness;
    private Random random;
    private HashMap<Integer, Byte> memory;

    HashMapRAM(RandomAccessMemory.Endianness endianness){
        this.endianness = endianness;
        random = new Random();
        memory = new HashMap<>();
    }

    @Override
    public Endianness getEndianness() {
        return endianness;
    }

    @Override
    public void setByte(int address, byte value){
        memory.put(address, value);
    }

    @Override
    public int getByte(int address){
        if (!memory.containsKey(address)) memory.put(address, (byte) random.nextInt());
        return Byte.toUnsignedInt(memory.get(address));
    }

}
