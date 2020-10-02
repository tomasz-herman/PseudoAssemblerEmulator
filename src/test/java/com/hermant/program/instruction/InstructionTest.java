package com.hermant.program.instruction;

import com.hermant.machine.register.InstructionPointer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

class InstructionTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 4, 24, 65536, 666666})
    public void shouldSetInstructionPointerAfterInstructionLength(int initialPointer) {
        int testInstLen = 4;
        InstructionPointer pointer = Mockito.mock(InstructionPointer.class);
        Mockito.when(pointer.get()).thenReturn(initialPointer);
        Instruction instruction = Mockito.mock(Instruction.class, Mockito.CALLS_REAL_METHODS);
        Mockito.when(instruction.instLength()).thenReturn(testInstLen);

        instruction.setInstructionPointer(pointer);

        Mockito.verify(pointer).set(initialPointer + testInstLen);
    }

}