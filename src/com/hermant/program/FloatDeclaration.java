package com.hermant.program;

import com.hermant.machine.RandomAccessMemory;

public class FloatDeclaration implements Declaration {

    private static final int BYTES = 4;

    private Float value;
    private int count;

    public FloatDeclaration(int count, Float value) {
        this.value = value;
        this.count = count;
    }

    @Override
    public int declare(RandomAccessMemory ram, int address) {
        if(value != null) for (int i = 0; i < count; i++) ram.setFloat(address + i * BYTES, value);
        return count * BYTES + address;
    }
}
