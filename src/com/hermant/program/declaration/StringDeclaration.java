package com.hermant.program.declaration;

import com.hermant.machine.RandomAccessMemory;

public class StringDeclaration implements Declaration{

    private String value;
    private int count;

    public StringDeclaration(String value) {
        this.value = value;
        this.count = value.length() + 1;
    }

    @Override
    public int declare(RandomAccessMemory ram, int address) {
        int i = 0;
        if(value != null) for (; i < value.length(); i++) ram.setByte(address + i , (byte)(Character.getNumericValue(value.charAt(i))));
        ram.setByte(address + i , (byte)0);
        return count + address;
    }
}
