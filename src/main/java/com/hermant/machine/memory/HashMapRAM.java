package com.hermant.machine.memory;

import java.util.HashMap;
import java.util.Random;

public class HashMapRAM extends RandomAccessMemory{

    private Random random;
    private HashMap<Integer, Byte> memory;

    public HashMapRAM(Endianness endianness){
        super(endianness);
        random = new Random();
        memory = new HashMap<>();
    }

    @Override
    public void setByte(int address, byte value){
        memory.put(address, value);
    }

    @Override
    public byte getByte(int address){
        if (!memory.containsKey(address)) memory.put(address, (byte) random.nextInt());
        return memory.get(address);
    }

}
