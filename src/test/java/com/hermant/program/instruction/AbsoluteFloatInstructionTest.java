package com.hermant.program.instruction;

import com.hermant.machine.Machine;
import com.hermant.machine.register.FloatingPointRegister;
import com.hermant.machine.register.InstructionPointer;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class AbsoluteFloatInstructionTest {

    @ParameterizedTest
    @ValueSource(floats = {-1f, 0f, 1f, -232323.22f, 3434334.45f, Float.MIN_VALUE, Float.NEGATIVE_INFINITY, Float.MAX_VALUE, -Float.MAX_VALUE})
    void shouldCalculateAbsOfValueWhenRun(float argument) {
        FloatingPointRegister fpr = Mockito.mock(FloatingPointRegister.class);
        InstructionPointer pointer = Mockito.mock(InstructionPointer.class);
        Machine machine = getMachineMock(argument, fpr, pointer);
        AbsoluteFloatInstruction instruction = new AbsoluteFloatInstruction();

        instruction.run(machine);

        Mockito.verify(fpr).set(0, Math.abs(argument));
    }

    @ParameterizedTest
    @ValueSource(bytes = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15})
    void shouldCalculateAbsInSpecifiedRegister(byte reg) {
        float minus_pi = -3f;
        FloatingPointRegister fpr = Mockito.mock(FloatingPointRegister.class);
        InstructionPointer pointer = Mockito.mock(InstructionPointer.class);
        Machine machine = getMachineMock(minus_pi, fpr, pointer);
        AbsoluteFloatInstruction instruction = new AbsoluteFloatInstruction();

        instruction.reg1 = reg;
        instruction.run(machine);

        Mockito.verify(fpr).set(reg, Math.abs(minus_pi));
    }

    @NotNull
    private Machine getMachineMock(float argument, FloatingPointRegister fpr, InstructionPointer pointer) {
        Machine machine = Mockito.mock(Machine.class);
        Mockito.when(machine.getInstructionPointer()).thenReturn(pointer);
        Mockito.when(machine.getFPR()).thenReturn(fpr);
        Mockito.when(fpr.get(Mockito.anyInt())).thenReturn(argument);
        return machine;
    }
}