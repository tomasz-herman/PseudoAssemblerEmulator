package com.hermant.program.instruction;

import com.hermant.machine.*;
import com.hermant.machine.ram.RandomAccessMemory;

public class LoadableInstruction extends Instruction {

    public LoadableInstruction(byte code, Byte reg1, Byte reg2, Short ramAddress) {
        super(code, reg1, reg2, ramAddress);
    }

    public int loadIntoMemory(RandomAccessMemory ram, int address){
        ram.setByte(address, code);
        ram.setByte(address + 1, (byte)(((reg1 & 0xf)<<4) | (reg2 & 0xf)));
        int length = (code & 0x10000000) == 0 ? 2 : 4;
        if(length == 2) return address + length;
//        ram.setByte(address + 3, (byte)((ramOffset & 0xff00)>>8));
//        ram.setByte(address + 2, (byte)(ramOffset & 0xff));
        ram.setByte(address + 2, (byte)((ramOffset & 0xff00)>>8));//BigEndian
        ram.setByte(address + 3, (byte)(ramOffset & 0xff));

        return address + length;
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        throw new IllegalStateException("Can't execute loadable instruction");
    }

    @Override
    public String instCode() {
        return "?";
    }
}