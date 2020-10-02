package com.hermant.program.instruction;

import com.hermant.machine.register.InstructionPointer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

class JumpOperationTest {

    @ValueSource(ints = {0, 678, 3333, 456, 1020, -345, 666, 11, 1, -1, Integer.MAX_VALUE})
    @ParameterizedTest
    public void shouldChangeInstructionPointerWhenRan(int jumpTo) {
        InstructionPointer pointer = Mockito.mock(InstructionPointer.class);
        JumpOperation operation = new JumpOperation() {};

        operation.jump(pointer, jumpTo);

        Mockito.verify(pointer).set(jumpTo);
    }

}