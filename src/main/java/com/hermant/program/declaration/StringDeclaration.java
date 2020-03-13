package com.hermant.program.declaration;

import com.hermant.machine.memory.RandomAccessMemory;
import com.hermant.serializer.Serializer;

public class StringDeclaration implements Declaration<String>{

    private String value;
    private int count;

    public StringDeclaration(String value) {
        this.value = value;
        this.count = value.length() + 1;
    }

    @Override
    public int declare(RandomAccessMemory ram, int address) {
        int i = 0;
        if(value != null) for (; i < value.length(); i++) ram.setByte(address + i , (byte)value.charAt(i));
        ram.setByte(address + i , (byte)0);
        return count + address;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public byte[] serialize() {
        byte[] array = new byte[count + 2];
        array[0] = (byte)Serializer.DeclarationType.STRING.ordinal();
        array[1] = (byte)count;
        array[2] = (byte)(count >> 8);
        for (int i = 0; i < value.length(); i++) {
            array[i + 3] = (byte)value.charAt(i);
        }
        return array;
    }
}
