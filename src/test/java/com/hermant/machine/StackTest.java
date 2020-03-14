package com.hermant.machine;

import com.hermant.machine.memory.ArrayRAM;
import com.hermant.machine.memory.Endianness;
import com.hermant.machine.memory.RandomAccessMemory;
import com.hermant.machine.register.GeneralPurposeRegister;
import com.hermant.program.instruction.MemoryOperation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    @Test
    public void emptyStack(){
        GeneralPurposeRegister gpr = new GeneralPurposeRegister();
        RandomAccessMemory ram = new ArrayRAM(Endianness.LittleEndian, 65536);
        gpr.set(GeneralPurposeRegister.STACK_POINTER, 0);
        gpr.set(GeneralPurposeRegister.STACK_FRAME_POINTER, 0);
        gpr.set(GeneralPurposeRegister.STACK_SECTION, 0);
        Stack s = new Stack(ram, gpr);
        assertEquals(0, s.size());
        assertEquals("Stack:\n", s.toString());
    }

    @Test
    public void pushIntThenPopInt(){
        GeneralPurposeRegister gpr = new GeneralPurposeRegister();
        RandomAccessMemory ram = new ArrayRAM(Endianness.LittleEndian, 65536);
        gpr.set(GeneralPurposeRegister.STACK_POINTER, 0);
        gpr.set(GeneralPurposeRegister.STACK_FRAME_POINTER, 0);
        gpr.set(GeneralPurposeRegister.STACK_SECTION, 0);
        Stack s = new Stack(ram, gpr);
        s.push(-1);
        assertEquals(1, s.size());
        assertEquals(-1, s.pop());
        assertEquals(0, s.size());
    }

    @Test
    public void pushIntThenPopFloat(){
        GeneralPurposeRegister gpr = new GeneralPurposeRegister();
        RandomAccessMemory ram = new ArrayRAM(Endianness.LittleEndian, 65536);
        gpr.set(GeneralPurposeRegister.STACK_POINTER, 0);
        gpr.set(GeneralPurposeRegister.STACK_FRAME_POINTER, 0);
        gpr.set(GeneralPurposeRegister.STACK_SECTION, 0);
        Stack s = new Stack(ram, gpr);
        s.push(Float.floatToIntBits((float)Math.PI));
        assertEquals(1, s.size());
        assertEquals((float)Math.PI, s.popFloat());
        assertEquals(0, s.size());
    }

    @Test
    public void pushFloatThenPopFloat(){
        GeneralPurposeRegister gpr = new GeneralPurposeRegister();
        RandomAccessMemory ram = new ArrayRAM(Endianness.LittleEndian, 65536);
        gpr.set(GeneralPurposeRegister.STACK_POINTER, 0);
        gpr.set(GeneralPurposeRegister.STACK_FRAME_POINTER, 0);
        gpr.set(GeneralPurposeRegister.STACK_SECTION, 0);
        Stack s = new Stack(ram, gpr);
        s.pushFloat((float)Math.PI);
        assertEquals(1, s.size());
        assertEquals((float)Math.PI, s.popFloat());
        assertEquals(0, s.size());
    }

    @Test
    public void pushFloatThenPopInt(){
        GeneralPurposeRegister gpr = new GeneralPurposeRegister();
        RandomAccessMemory ram = new ArrayRAM(Endianness.LittleEndian, 65536);
        gpr.set(GeneralPurposeRegister.STACK_POINTER, 0);
        gpr.set(GeneralPurposeRegister.STACK_FRAME_POINTER, 0);
        gpr.set(GeneralPurposeRegister.STACK_SECTION, 0);
        Stack s = new Stack(ram, gpr);
        s.pushFloat((float)Math.PI);
        assertEquals(1, s.size());
        assertEquals(Float.floatToIntBits((float)Math.PI), s.pop());
        assertEquals(0, s.size());
    }

    @Test
    public void overflowStack(){
        GeneralPurposeRegister gpr = new GeneralPurposeRegister();
        RandomAccessMemory ram = new ArrayRAM(Endianness.LittleEndian, 65536);
        gpr.set(GeneralPurposeRegister.STACK_POINTER, 0);
        gpr.set(GeneralPurposeRegister.STACK_FRAME_POINTER, 0);
        gpr.set(GeneralPurposeRegister.STACK_SECTION, 0);
        Stack s = new Stack(ram, gpr);
        for (int i = 0; i < 24576; i++) s.push(i);
        assertEquals(8192, s.size());
        assertEquals(24575, s.pop());
    }

    @Test
    public void manuallyAllocate(){
        GeneralPurposeRegister gpr = new GeneralPurposeRegister();
        RandomAccessMemory ram = new ArrayRAM(Endianness.LittleEndian, 65536);
        gpr.set(GeneralPurposeRegister.STACK_POINTER, 0);
        gpr.set(GeneralPurposeRegister.STACK_FRAME_POINTER, 0);
        gpr.set(GeneralPurposeRegister.STACK_SECTION, 0);
        Stack s = new Stack(ram, gpr);
        MemoryOperation op = new MemoryOperation() {};
        int alloc = op.getMemoryAddress(gpr, GeneralPurposeRegister.STACK_POINTER, (short)-4);
        gpr.set(GeneralPurposeRegister.STACK_POINTER, alloc);
        ram.setInteger(gpr.get(GeneralPurposeRegister.STACK_POINTER), -1);
        assertEquals(1, s.size());
        assertEquals(-1, s.pop());
    }

}