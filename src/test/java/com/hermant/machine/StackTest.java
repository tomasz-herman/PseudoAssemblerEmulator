package com.hermant.machine;

import com.hermant.machine.memory.ArrayRAM;
import com.hermant.machine.memory.Endianness;
import com.hermant.machine.memory.RandomAccessMemory;
import com.hermant.machine.register.GeneralPurposeRegister;
import com.hermant.program.instruction.MemoryOperation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    @ParameterizedTest
    @ValueSource(ints = {0, 1, -1, 3, 12, -11, 454545, -56574, 22222222, Integer.MAX_VALUE, Integer.MIN_VALUE })
    public void pushIntThenPopInt(int argument){
        GeneralPurposeRegister gpr = new GeneralPurposeRegister();
        RandomAccessMemory ram = new ArrayRAM(Endianness.LittleEndian, 65536);
        gpr.set(GeneralPurposeRegister.STACK_POINTER, 0);
        gpr.set(GeneralPurposeRegister.STACK_FRAME_POINTER, 0);
        gpr.set(GeneralPurposeRegister.STACK_SECTION, 0);
        Stack s = new Stack(ram, gpr);
        s.push(argument);
        assertEquals(1, s.size());
        assertEquals(argument, s.pop());
        assertEquals(0, s.size());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, -1, 3, 12, -11, 454545, -56574, 22222222, Integer.MAX_VALUE, Integer.MIN_VALUE })
    public void pushIntThenPopFloat(int argument){
        GeneralPurposeRegister gpr = new GeneralPurposeRegister();
        RandomAccessMemory ram = new ArrayRAM(Endianness.LittleEndian, 65536);
        gpr.set(GeneralPurposeRegister.STACK_POINTER, 0);
        gpr.set(GeneralPurposeRegister.STACK_FRAME_POINTER, 0);
        gpr.set(GeneralPurposeRegister.STACK_SECTION, 0);
        Stack s = new Stack(ram, gpr);
        s.push(argument);
        assertEquals(1, s.size());
        assertEquals(Float.intBitsToFloat(argument), s.popFloat());
        assertEquals(0, s.size());
    }

    @ParameterizedTest
    @ValueSource(floats = { (float)Math.PI, 0.0f, 0.01f, -0.01f, -0.0f,
            Float.MAX_VALUE, Float.MIN_VALUE, Float.NaN, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY })
    public void pushFloatThenPopFloat(float argument){
        GeneralPurposeRegister gpr = new GeneralPurposeRegister();
        RandomAccessMemory ram = new ArrayRAM(Endianness.LittleEndian, 65536);
        gpr.set(GeneralPurposeRegister.STACK_POINTER, 0);
        gpr.set(GeneralPurposeRegister.STACK_FRAME_POINTER, 0);
        gpr.set(GeneralPurposeRegister.STACK_SECTION, 0);
        Stack s = new Stack(ram, gpr);
        s.pushFloat(argument);
        assertEquals(1, s.size());
        assertEquals(argument, s.popFloat());
        assertEquals(0, s.size());
    }

    @ParameterizedTest
    @ValueSource(floats = { (float)Math.PI, 0.0f, 0.01f, -0.01f, -0.0f,
            Float.MAX_VALUE, Float.MIN_VALUE, Float.NaN, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY })
    public void pushFloatThenPopInt(float argument){
        GeneralPurposeRegister gpr = new GeneralPurposeRegister();
        RandomAccessMemory ram = new ArrayRAM(Endianness.LittleEndian, 65536);
        gpr.set(GeneralPurposeRegister.STACK_POINTER, 0);
        gpr.set(GeneralPurposeRegister.STACK_FRAME_POINTER, 0);
        gpr.set(GeneralPurposeRegister.STACK_SECTION, 0);
        Stack s = new Stack(ram, gpr);
        s.pushFloat(argument);
        assertEquals(1, s.size());
        assertEquals(Float.floatToIntBits(argument), s.pop());
        assertEquals(0, s.size());
    }

    @ParameterizedTest
    @ValueSource(ints = { 24576, 343542, 56565, 16384, 77777, 65536, 65535, 1000000})
    public void overflowStack(int argument){
        GeneralPurposeRegister gpr = new GeneralPurposeRegister();
        RandomAccessMemory ram = new ArrayRAM(Endianness.LittleEndian, 65536);
        gpr.set(GeneralPurposeRegister.STACK_POINTER, 0);
        gpr.set(GeneralPurposeRegister.STACK_FRAME_POINTER, 0);
        gpr.set(GeneralPurposeRegister.STACK_SECTION, 0);
        Stack s = new Stack(ram, gpr);
        for (int i = 0; i < argument; i++) s.push(i);
        assertEquals(argument % 16384, s.size());
        assertEquals(argument - 1, s.pop());
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, 1, 2, 3, 4, 5, 10, 20, 35, 100, 111, 200, 453, 16000})
    public void manuallyAllocate(int argument){
        GeneralPurposeRegister gpr = new GeneralPurposeRegister();
        RandomAccessMemory ram = new ArrayRAM(Endianness.LittleEndian, 65536);
        gpr.set(GeneralPurposeRegister.STACK_POINTER, 0);
        gpr.set(GeneralPurposeRegister.STACK_FRAME_POINTER, 0);
        gpr.set(GeneralPurposeRegister.STACK_SECTION, 0);
        Stack s = new Stack(ram, gpr);
        MemoryOperation op = new MemoryOperation() {};
        int alloc = op.getMemoryAddress(gpr, GeneralPurposeRegister.STACK_POINTER, (short)(-4 * argument));
        gpr.set(GeneralPurposeRegister.STACK_POINTER, alloc);
        ram.setInteger(gpr.get(GeneralPurposeRegister.STACK_POINTER), -1);
        assertEquals(argument, s.size());
        assertEquals(-1, s.pop());
    }

}