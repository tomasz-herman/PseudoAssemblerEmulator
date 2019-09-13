package com.hermant.program.declaration;

import com.hermant.machine.ram.RandomAccessMemory;
import com.hermant.serializer.Serializable;

public interface Declaration<T> extends Serializable {

    enum Type {
        anInteger, aFloat, aByte, aChar, aString
    }

    int declare(RandomAccessMemory ram, int address);

    T getValue();

    int getCount();
}
