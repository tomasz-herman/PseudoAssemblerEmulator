package com.hermant.machine;

import com.hermant.machine.ram.RandomAccessMemory;
import com.hermant.machine.register.Register;

import static com.hermant.machine.register.Register.STACK_POINTER;
import static com.hermant.machine.register.Register.STACK_SECTION;

public class Stack {

    private RandomAccessMemory ram;
    private Register reg;
    private static final int MAX_SIZE = 0x10000;

    Stack(RandomAccessMemory ram, Register reg){
        this.ram = ram;
        this.reg = reg;
    }

    private int bottom(){
        return reg.getInteger(STACK_SECTION);
    }

    private int pointer(){
        return reg.getInteger(STACK_POINTER);
    }

    public boolean empty(){
        return pointer() == bottom() + MAX_SIZE;
    }

    private boolean full(){ return pointer() == bottom(); }

    private boolean illegalState(){
        return pointer() < bottom() || pointer() > bottom() + MAX_SIZE;
    }

    public void push(int val){
        if(illegalState())throw new IllegalStateException("stack illegal state");
        if(full())throw new IllegalStateException("stack overflow");
        reg.setInteger(STACK_POINTER, reg.getInteger(STACK_POINTER) - 4);
        ram.setInteger(pointer(), val);
        if(illegalState())throw new IllegalStateException("stack illegal state");
    }

    public int pop(){
        if(illegalState())throw new IllegalStateException("stack illegal state");
        if(empty())throw new IllegalStateException("stack underflow");
        reg.setInteger(STACK_POINTER, reg.getInteger(STACK_POINTER) + 4);
        if(illegalState())throw new IllegalStateException("stack illegal state");
        return ram.getInteger(pointer()-4);
    }
}
