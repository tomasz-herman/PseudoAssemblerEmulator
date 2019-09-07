package com.hermant.program.instruction;

import com.hermant.machine.*;
import com.hermant.machine.ram.RandomAccessMemory;
import com.hermant.machine.register.InstructionPointer;

public class LoadableInstruction extends Instruction {

    private byte code;

    public LoadableInstruction(byte code, byte reg1, byte reg2, short ramOffset) {
        this.code = code;
        this.reg1 = reg1;
        this.reg2 = reg2;
        this.ramOffset = ramOffset;
    }

    public int loadIntoMemory(boolean debug, RandomAccessMemory ram, int address){
        ram.setByte(address, code);
        ram.setByte(address + 1, (byte)(((reg1 & 0xf)<<4) | (reg2 & 0xf)));
        int length = instLength();

        if(length==4) ram.setShort(address + 2, ramOffset);

        if(debug) System.out.println(String.format("%1$08X",address) + " | " + InstructionFactory.fetchNextInstruction(ram, new InstructionPointer(address)));

        return address + length;
    }

    @Override
    public final boolean run(Machine m){
        throw new IllegalStateException("Can't execute loadable instruction");
    }

    @Override
    public final byte code() {
        return code;
    }

    @Override
    public final int instLength() {
        return InstructionFactory.INSTRUCTION_LENGTHS[code + 128];
    }

    @Override
    public final String instCode() {
        return InstructionFactory.INSTRUCTION_CODES[code + 128];
    }
}