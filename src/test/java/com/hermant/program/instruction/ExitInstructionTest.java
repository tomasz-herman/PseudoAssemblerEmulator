package com.hermant.program.instruction;

import com.hermant.machine.Machine;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class ExitInstructionTest {

    @Test
    void run() throws NoSuchFieldException, IllegalAccessException {
        Machine machine = new Machine(false, false);
        Field running = Machine.class.getDeclaredField("running");
        running.setAccessible(true);
        running.set(machine, true);
        ExitInstruction instruction = (ExitInstruction)InstructionUtils.getInstruction(Instruction.EXIT);
        assertNotNull(instruction);
        instruction.run(machine);
        assertFalse(running.getBoolean(machine));
    }

}