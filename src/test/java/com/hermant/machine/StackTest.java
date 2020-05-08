package com.hermant.machine;

import com.hermant.machine.memory.ArrayRAM;
import com.hermant.machine.memory.RandomAccessMemory;
import com.hermant.machine.register.GeneralPurposeRegister;
import com.hermant.program.instruction.MemoryOperation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.hermant.machine.memory.Endianness.LittleEndian;
import static com.hermant.machine.register.GeneralPurposeRegister.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StackTest {

    private GeneralPurposeRegister gpr;
    private RandomAccessMemory ram;
    private Stack s;

    @BeforeEach
    public void prepare() {
        gpr = new GeneralPurposeRegister();
        ram = new ArrayRAM(LittleEndian, 65536);
        gpr.set(STACK_POINTER, 0);
        gpr.set(STACK_FRAME_POINTER, 0);
        gpr.set(STACK_SECTION, 0);
        s = new Stack(ram, gpr);
    }

    @AfterEach
    public void cleanup() {
        ram.free();
    }

    @Test
    public void emptyStack() {
        assertEquals(0, s.size());
        assertEquals("Empty stack", s.toString());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, -1, 3, 12, -11, 454545, -56574, 22222222, Integer.MAX_VALUE, Integer.MIN_VALUE})
    public void pushIntThenPopInt(int argument) {
        s.push(argument);
        assertEquals(1, s.size());
        assertEquals(argument, s.pop());
        assertEquals(0, s.size());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, -1, 3, 12, -11, 454545, -56574, 22222222, Integer.MAX_VALUE, Integer.MIN_VALUE})
    public void pushIntThenPopFloat(int argument) {
        s.push(argument);
        assertEquals(1, s.size());
        assertEquals(Float.intBitsToFloat(argument), s.popFloat());
        assertEquals(0, s.size());
    }

    @ParameterizedTest
    @ValueSource(floats = {(float) Math.PI, 0.0f, 0.01f, -0.01f, -0.0f,
            Float.MAX_VALUE, Float.MIN_VALUE, Float.NaN, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY})
    public void pushFloatThenPopFloat(float argument) {
        s.pushFloat(argument);
        assertEquals(1, s.size());
        assertEquals(argument, s.popFloat());
        assertEquals(0, s.size());
    }

    @ParameterizedTest
    @ValueSource(floats = {(float) Math.PI, 0.0f, 0.01f, -0.01f, -0.0f,
            Float.MAX_VALUE, Float.MIN_VALUE, Float.NaN, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY})
    public void pushFloatThenPopInt(float argument) {
        s.pushFloat(argument);
        assertEquals(1, s.size());
        assertEquals(Float.floatToIntBits(argument), s.pop());
        assertEquals(0, s.size());
    }

    @ParameterizedTest
    @ValueSource(ints = {24576, 343542, 56565, 16384, 77777, 65536, 65535, 1000000})
    public void overflowStack(int argument) {
        for (int i = 0; i < argument; i++) s.push(i);
        assertEquals(argument % 16384, s.size());
        assertEquals(argument - 1, s.pop());
    }


    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 10, 20, 35, 100, 111, 200, 453, 16000})
    public void manuallyAllocate(int argument) {
        MemoryOperation op = new MemoryOperation() {
        };
        int alloc = op.getMemoryAddress(gpr, STACK_POINTER, (short) (-4 * argument));
        gpr.set(STACK_POINTER, alloc);
        ram.setInteger(gpr.get(STACK_POINTER), -1);
        assertEquals(argument, s.size());
        assertEquals(-1, s.pop());
    }

    static Stream<Arguments> stackCorruptingRegisters() {
        return Stream.of(
                Arguments.of(0, 1),
                Arguments.of(0, 2),
                Arguments.of(0, 3),
                Arguments.of(1, 0),
                Arguments.of(2, 0),
                Arguments.of(3, 0),
                Arguments.of(0x00010000, 0x00000000),
                Arguments.of(0x80000000, 0x00000000),
                Arguments.of(0x00001340, 0x00001643),
                Arguments.of(0x22FC0000, 0x3D330000),
                Arguments.of(0x000129A6, 0x0001F39F),
                Arguments.of(0x00010001, 0xF0000003)
        );
    }

    @ParameterizedTest
    @MethodSource("stackCorruptingRegisters")
    public void toString(int pointer, int section) {
        gpr.set(STACK_POINTER, pointer);
        gpr.set(STACK_SECTION, section);
        assertEquals("Corrupted stack", s.toString());
    }

    static Stream<int[]> multipleValues() {
        Random random = new Random(0);
        return Stream.of(
                new int[]{3},
                new int[]{1, 2, 3, 4, 5, 6, 7, 8},
                new int[]{1, -1, 1, -1, 1, -1, 1, -1},
                new int[]{0, 1, 2, 3, 4, 5, 10, 20, 35, 100, 111, 200, 453, 16000},
                new int[]{1, 2, 3, 0x10000, 0xF0000, 0xFFF0000, 0x80000000, 0xFFFF0000, -1, -2, -3, -4},
                IntStream.generate(random::nextInt).limit(64).toArray(),
                IntStream.generate(random::nextInt).limit(0x1fff).toArray(),
                IntStream.generate(random::nextInt).limit(0x3fff).toArray()
        );
    }

    @ParameterizedTest
    @MethodSource("multipleValues")
    public void toString(int[] values) {
        for (int value : values) s.push(value);
        StringBuilder expected = new StringBuilder();
        expected.append("Stack:\n");
        for (int i = values.length - 1; i >= 0; i--) expected.append(values[i]).append("\n");
        expected.deleteCharAt(expected.length() - 1);
        assertEquals(expected.toString(), s.toString());
    }

    @ParameterizedTest
    @MethodSource("multipleValues")
    public void pushMultipleThenPopMultiple(int[] values) {
        for (int value : values) s.push(value);
        assertEquals(values.length, s.size());
        for (int i = values.length - 1; i >= 0; i--) assertEquals(values[i], s.pop());
        assertEquals(0, s.size());
    }
}
