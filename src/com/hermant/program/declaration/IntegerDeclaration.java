package com.hermant.program.declaration;

import com.hermant.machine.ram.RandomAccessMemory;

public class IntegerDeclaration implements Declaration{

    private static final int BYTES = 4;

    private Integer value;
    private int count;

    public IntegerDeclaration(int count, Integer value) {
        this.value = value;
        this.count = count;
    }

    @Override
    public int declare(RandomAccessMemory ram, int address) {
        if(value != null) for (int i = 0; i < count; i++) ram.setInteger(address + i * BYTES, value);
        return count * BYTES + address;
    }
}
