package com.hermant.program.instruction;

import com.hermant.machine.Machine;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ExitInstructionTest {

    @Test
    void run() {
        Machine machine = Mockito.mock(Machine.class);
        ExitInstruction instruction = new ExitInstruction();
        instruction.run(machine);
        Mockito.verify(machine).stop();
    }

}