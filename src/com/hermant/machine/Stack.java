package com.hermant.machine;

import com.hermant.machine.ram.RandomAccessMemory;
import com.hermant.machine.register.GeneralPurposeRegister;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.hermant.machine.register.GeneralPurposeRegister.STACK_POINTER;
import static com.hermant.machine.register.GeneralPurposeRegister.STACK_SECTION;

public class Stack {

    private RandomAccessMemory ram;
    private GeneralPurposeRegister reg;

    Stack(RandomAccessMemory ram, GeneralPurposeRegister reg){
        this.ram = ram;
        this.reg = reg;
    }

    private int pointer(){
        return reg.get(STACK_POINTER);
    }

    private int section() {
        return reg.get(STACK_SECTION);
    }

    public void push(int val){
        int pointer = pointer();
        pointer = pointer & 0xffff0000 | (0x0000ffff & (pointer - 4));
        reg.set(STACK_POINTER, pointer);
        ram.setInteger(pointer, val);
    }

    public int pop(){
        int pointer = pointer();
        int result = ram.getInteger(pointer);
        pointer = pointer & 0xffff0000 | (0x0000ffff & (pointer + 4));
        reg.set(STACK_POINTER, pointer);
        return result;
    }

    public void pushFloat(float val){
        int pointer = pointer();
        pointer = pointer & 0xffff0000 | (0x0000ffff & (pointer - 4));
        reg.set(STACK_POINTER, pointer);
        ram.setFloat(pointer, val);
    }

    public float popFloat(){
        int pointer = pointer();
        float result = ram.getFloat(pointer);
        pointer = pointer & 0xffff0000 | (0x0000ffff & (pointer + 4));
        reg.set(STACK_POINTER, pointer);
        return result;
    }

    @Override
    public String toString() {
        if (((pointer() | section()) & 0b11) != 0 ||
                ((pointer() ^ section()) & 0xffff0000) != 0)
            return "Corrupted stack";
        return IntStream.iterate(
                pointer(),
                address -> address != section(),
                address -> address & 0xffff0000 | (0x0000ffff & (address + 4))).mapToObj(
                        address -> String.valueOf(ram.getInteger(address)) + '\n').collect(
                                Collectors.joining("", "Stack:\n", ""));
    }
}
