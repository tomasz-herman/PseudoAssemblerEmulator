package com.hermant.program.declaration;

import com.hermant.machine.ram.RandomAccessMemory;

public class CharDeclaration implements Declaration{

    private static final int BYTES = 1;

    private Character value;
    private int count;

    public CharDeclaration(int count, Character value) {
        this.value = value;
        this.count = count;
    }

    @Override
    public int declare(RandomAccessMemory ram, int address) {
        if(value != null) for (int i = 0; i < count; i++) ram.setByte(address + i * BYTES, (byte)value.charValue());
        return count * BYTES + address;
    }
}
