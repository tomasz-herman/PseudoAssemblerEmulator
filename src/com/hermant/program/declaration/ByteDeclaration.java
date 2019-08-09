package com.hermant.program.declaration;

import com.hermant.machine.RandomAccessMemory;

public class ByteDeclaration implements Declaration{

    private static final int BYTES = 1;

    private Byte value;
    private int count;

    public ByteDeclaration(int count, Byte value) {
        this.value = value;
        this.count = count;
    }

    @Override
    public int declare(RandomAccessMemory ram, int address) {
        if(value != null) for (int i = 0; i < count; i++) ram.setByte(address + i * BYTES, value);
        return count * BYTES + address;
    }
}
