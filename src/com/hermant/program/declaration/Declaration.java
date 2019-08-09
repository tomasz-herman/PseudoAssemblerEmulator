package com.hermant.program.declaration;

import com.hermant.machine.RandomAccessMemory;

import java.io.Serializable;

public interface Declaration extends Serializable {

    enum Type {
        anInteger, aFloat, aByte, aChar, aString
    }

    int declare(RandomAccessMemory ram, int address);
}
