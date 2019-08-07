package com.hermant.program;

import com.hermant.machine.RandomAccessMemory;

//TODO:REWRITE, USE INHERITANCE
public class Declaration {

    public enum Type {
        anInteger, aFloat, aByte, aChar, None
    }

    private final static int INT_SIZE = 4;
    private final static int FLOAT_SIZE = 4;
    private final static int CHAR_SIZE = 1;
    private final static int BYTE_SIZE = 1;

    private Type type;
    private int count;
    private Float aFloat;
    private Integer integer;
    private Byte aByte;
    private Character character;
    private short bytes;

    public Declaration(int count, float aFloat) {
        this.type = Type.aFloat;
        this.count = count;
        this.aFloat = aFloat;
        this.bytes = FLOAT_SIZE;
    }

    public Declaration(int count, int integer) {
        this.type = Type.anInteger;
        this.count = count;
        this.integer = integer;
        this.bytes = INT_SIZE;
    }

    public Declaration(int count, byte aByte) {
        this.type = Type.aByte;
        this.count = count;
        this.aByte = aByte;
        this.bytes = BYTE_SIZE;
    }

    public Declaration(int count, char character) {
        this.type = Type.aChar;
        this.count = count;
        this.character = character;
        this.bytes = CHAR_SIZE;
    }

    public Declaration(int count, short bytes) {
        this.type = Type.None;
        this.count = count;
        this.bytes = bytes;
    }

    public int declare(RandomAccessMemory ram, int address){
        switch (type){
            case None -> {}
            case anInteger -> { for (int i = 0; i < count; i++) ram.setInteger(address + i * bytes, integer); }
            case aFloat -> { for (int i = 0; i < count; i++) ram.setFloat(address + i * bytes, aFloat); }
            case aByte -> { for (int i = 0; i < count; i++) ram.setByte(address + i * bytes, aByte); }
            case aChar -> { for (int i = 0; i < count; i++) ram.setByte(address + i * bytes, (byte)character.charValue()); }
        }
        return count * bytes + address;
    }
}
