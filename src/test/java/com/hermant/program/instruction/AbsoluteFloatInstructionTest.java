package com.hermant.program.instruction;

import com.hermant.machine.Machine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbsoluteFloatInstructionTest {

    @Test
    void run() {
        Machine machine = new Machine(false, false);
        float[] values = {-1f, 0f, 1f, -232323.22f, 3434334.45f, Float.MIN_VALUE, Float.NEGATIVE_INFINITY, Float.MAX_VALUE, -Float.MAX_VALUE};
        for (int i = 0; i < values.length; i++) {
            machine.getFPR().set(i, values[i]);
            AbsoluteFloatInstruction instruction = (AbsoluteFloatInstruction)InstructionUtils.getInstruction(Instruction.ABSOLUTE_FLOAT);
            assertNotNull(instruction);
            instruction.reg1 = (byte)i;
            instruction.run(machine);
            assertEquals(Math.abs(values[i]), machine.getFPR().get(i));
        }
    }
}