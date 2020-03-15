package com.hermant.program.instruction;

import com.hermant.machine.Machine;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import static org.junit.jupiter.api.Assertions.*;

class JumpOperationTest {

    @ValueSource(ints = {0, 678, 3333, 456, 1020, -345, 666, 11, 1, -1, Integer.MAX_VALUE})
    @ParameterizedTest
    public void run(int argument) {
        Machine machine = new Machine(false, false);
        machine.getInstructionPointer().set(0);
        JumpOperation op = new JumpOperation() {};
        op.jump(machine.getInstructionPointer(), argument);
        assertEquals(argument, machine.getInstructionPointer().get());
    }

}