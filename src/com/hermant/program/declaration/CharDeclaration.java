package com.hermant.program.declaration;

import com.hermant.machine.ram.RandomAccessMemory;
import com.hermant.serializer.Serializer;

public class CharDeclaration implements Declaration<Character>{

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

    @Override
    public Character getValue() {
        return value;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public byte[] toByteArray() {
        boolean many = count != 1;
        boolean hasValue = value != null;
        byte[] array = new byte[1 + (many ? 2 : 0) + (hasValue ? BYTES : 0)];
        int i = 0;
        array[i++] = many && hasValue ? (byte) Serializer.DeclarationType.MANY_BYTES.ordinal() :
                !many && hasValue ? (byte)Serializer.DeclarationType.SINGLE_BYTE.ordinal() :
                        many ? (byte)Serializer.DeclarationType.MANY_EMPTY_BYTES.ordinal() :
                                (byte)Serializer.DeclarationType.EMPTY_BYTE.ordinal();
        if(many){
            array[i++] = (byte)count;
            array[i++] = (byte)(count >> 8);
        }
        if(hasValue)
            array[i] = (byte)value.charValue();
        return array;
    }
}
