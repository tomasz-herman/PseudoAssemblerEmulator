package com.hermant.program.instruction;

import com.hermant.machine.register.InstructionPointer;

/**
 * If {@link Instruction} implements this interface the method {@link Instruction#debug(InstructionPointer)} won't yield
 * extra new line which will be placed instead when Instruction finishes printing it's output.
 * @see Instruction#debug(InstructionPointer)
 */
public interface OutputOperation{ }
