package com.hermant.program.instruction;

import com.hermant.machine.*;
import com.hermant.machine.ram.RandomAccessMemory;
import com.hermant.serializer.Serializable;

public class LoadableInstruction extends Instruction implements Serializable {

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

        if(debug) System.out.println(String.format("%1$08X",address) + " | " + InstructionFactory.fetchInstruction(ram, address));

        return address + length;
    }

    public byte[] serialize(){
        byte[] array = new byte[instLength()];
        array[0] = code;
        array[1] = (byte)(((reg1 & 0xf)<<4) | (reg2 & 0xf));
        if(instLength() == 2) return array;
        array[2] = (byte)ramOffset;
        array[3] = (byte)(ramOffset >> 8);
        return array;
    }

    @Override
    public final void run(Machine m){
        throw new IllegalStateException("Can't execute loadable instruction");
    }

    @Override
    public final byte code() {
        return code;
    }

    @Override
    public final int instLength() {
        return InstructionFactory.INSTRUCTION_LENGTHS[code & 255];
    }

    @Override
    public final String instCode() {
        return InstructionFactory.INSTRUCTION_CODES[code & 255];
    }
}