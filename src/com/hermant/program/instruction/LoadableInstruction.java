package com.hermant.program.instruction;

import com.hermant.machine.*;
import com.hermant.machine.ram.RandomAccessMemory;
import com.hermant.machine.register.InstructionPointer;

public class LoadableInstruction extends Instruction {

    public LoadableInstruction(byte code, Byte reg1, Byte reg2, Short ramOffset) {
        super(code, reg1, reg2, ramOffset);
    }

    public int loadIntoMemory(boolean debug, RandomAccessMemory ram, int address){
        ram.setByte(address, code);
        ram.setByte(address + 1, (byte)(((reg1 & 0xf)<<4) | (reg2 & 0xf)));
        int length = instLength();

        if(length==4) ram.setShort(address + 2, ramOffset);

        if(debug) System.out.println(InstructionFactory.fetchNextInstruction(ram, new InstructionPointer(address)));

        return address + length;
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        throw new IllegalStateException("Can't execute loadable instruction");
    }

    @Override
    public int instLength() {
        return InstructionFactory.INSTRUCTION_LENGTHS[code + 128];
    }

    @Override
    public String instCode() {
        return "?";
    }
}