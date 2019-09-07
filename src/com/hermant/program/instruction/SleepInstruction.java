package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class SleepInstruction extends Instruction implements MemoryOperation {

    @Override
    public final boolean run(Machine m) {
        setInstructionPointer(m.getInstructionPointer());
        final int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        try {
            Thread.sleep(m.getRam().getInteger(ramAddress));
        } catch (InterruptedException ignored) { }
        return true;
    }

    @Override
    public final byte code() {
        return SLEEP;
    }

    @Override
    public final int instLength() {
        return 4;
    }

    @Override
    public final String instCode() {
        return "SLEEP";
    }
}
