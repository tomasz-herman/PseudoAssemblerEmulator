package com.hermant.program;

import com.hermant.machine.RandomAccessMemory;

public interface Declaration {

    enum Type {
        anInteger, aFloat, aByte, aChar, aString
    }

    int declare(RandomAccessMemory ram, int address);
}
