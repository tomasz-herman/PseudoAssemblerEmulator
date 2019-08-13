package com.hermant.machine;

import com.hermant.machine.ram.RandomAccessMemory;
import com.hermant.machine.register.Register;

import static com.hermant.machine.register.Register.STACK_POINTER;

public class Stack {

    private RandomAccessMemory ram;
    private Register reg;

    Stack(RandomAccessMemory ram, Register reg){
        this.ram = ram;
        this.reg = reg;
    }

    private int pointer(){
        return reg.getInteger(STACK_POINTER);
    }

    public void push(int val){
        int pointer = pointer();
        pointer = pointer & 0xffff0000 | (0x0000ffff & (pointer - 4));
        reg.setInteger(STACK_POINTER, pointer);
        ram.setInteger(pointer, val);
    }

    public int pop(){
        int pointer = pointer();
        int result = ram.getInteger(pointer);
        pointer = pointer & 0xffff0000 | (0x0000ffff & (pointer + 4));
        reg.setInteger(STACK_POINTER, pointer);
        return result;
    }
}
