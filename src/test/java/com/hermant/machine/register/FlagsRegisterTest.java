package com.hermant.machine.register;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlagsRegisterTest {

    @Test
    void setAndGetFlags() {
        FlagsRegister flagsRegister = new FlagsRegister();
        flagsRegister.setFlags(0b11111111111111111111111111111111);
        assertEquals("OF=" + 1 + ", SF=" + 1+ ", ZF=" + 1 + ", PF=" + 1 + ", CF=" + 1, flagsRegister.toString());
        flagsRegister.setFlags(0b00000000000000000000000000000000);
        assertEquals("OF=" + 0 + ", SF=" + 0+ ", ZF=" + 0 + ", PF=" + 0 + ", CF=" + 0, flagsRegister.toString());
    }
}