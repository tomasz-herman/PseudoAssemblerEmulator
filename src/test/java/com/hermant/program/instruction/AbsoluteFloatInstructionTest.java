package com.hermant.program.instruction;

import com.hermant.machine.Machine;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class AbsoluteFloatInstructionTest {

    @ParameterizedTest
    @ValueSource(floats = {-1f, 0f, 1f, -232323.22f, 3434334.45f, Float.MIN_VALUE, Float.NEGATIVE_INFINITY, Float.MAX_VALUE, -Float.MAX_VALUE})
    void run(float argument) {
        Machine machine = new Machine(false, false);
        for (int i = 0; i < 16; i++) {
            machine.getFPR().set(i, argument);
            AbsoluteFloatInstruction instruction = (AbsoluteFloatInstruction)InstructionUtils.getInstruction(Instruction.ABSOLUTE_FLOAT);
            assertNotNull(instruction);
            instruction.reg1 = (byte)i;
            instruction.run(machine);
            assertEquals(Math.abs(argument), machine.getFPR().get(i));
        }
    }
}