package com.hermant.program.instruction;

import com.hermant.machine.Machine;

/**
 * If {@link Instruction} implements this interface the method {@link Instruction#debug(Machine)} won't yield
 * extra new line which will be placed instead when Instruction finishes printing it's output.
 * @see Instruction#debug(Machine)
 */
public interface OutputOperation{ }
