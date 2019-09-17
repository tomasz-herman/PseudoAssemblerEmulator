package com.hermant.program.declaration;

import com.hermant.machine.memory.RandomAccessMemory;
import com.hermant.serializer.Serializer;

public class IntegerDeclaration implements Declaration<Integer>{

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

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public byte[] serialize() {
        boolean many = count != 1;
        boolean hasValue = value != null;
        byte[] array = new byte[1 + (many ? 2 : 0) + (hasValue ? BYTES : 0)];
        int i = 0;
        array[i++] = many && hasValue ? (byte) Serializer.DeclarationType.MANY_INTS.ordinal() :
                !many && hasValue ? (byte)Serializer.DeclarationType.SINGLE_INT.ordinal() :
                        many ? (byte)Serializer.DeclarationType.MANY_EMPTY_INTS.ordinal() :
                                (byte)Serializer.DeclarationType.EMPTY_INT.ordinal();
        if(many){
            array[i++] = (byte)count;
            array[i++] = (byte)(count >> 8);
        }
        if(hasValue){
            int val = value;
            array[i++] = (byte)val;
            array[i++] = (byte)(val >> 8);
            array[i++] = (byte)(val >> 16);
            array[i] = (byte)(val >> 24);
        }
        return array;
    }
}
