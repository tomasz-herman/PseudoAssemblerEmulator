package com.hermant.machine.ram;

import java.util.HashMap;
import java.util.Random;

public class HashMapRAM implements RandomAccessMemory{

    private Random random;
    private HashMap<Integer, Byte> memory;

    public HashMapRAM(){
        random = new Random();
        memory = new HashMap<>();
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
