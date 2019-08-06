package com.hermant.program;

import com.hermant.machine.RandomAccessMemory;

public class Declaration {

    public enum Type {
        anInteger, aFloat, None
    }

    private Type type;
    private int count;
    private Float aFloat;
    private Integer integer;

    public Declaration(int count, float aFloat) {
        this.type = Type.aFloat;
        this.count = count;
        this.aFloat = aFloat;
        this.integer = null;
    }

    public Declaration(int count, int integer) {
        this.type = Type.anInteger;
        this.count = count;
        this.aFloat = null;
        this.integer = integer;
    }

    public Declaration(int count) {
        this.type = Type.None;
        this.count = count;
        this.aFloat = null;
        this.integer = null;
    }

    public int declare(RandomAccessMemory ram, int address){
        switch (type){
            case None -> {}
            case anInteger -> { for (int i = 0; i < count; i++) ram.setInteger(address + i * 4, integer); }
            case aFloat -> { for (int i = 0; i < count; i++) ram.setFloat(address + i * 4, aFloat); }
        }
        return count * 4 + address;
    }
}
