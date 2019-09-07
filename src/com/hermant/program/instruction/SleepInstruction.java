package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class SleepInstruction extends Instruction implements MemoryOperation {

    @Override
    public boolean execute(Machine m, boolean debug) {
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        try {
            Thread.sleep(m.getRam().getInteger(ramAddress));
        } catch (InterruptedException ignored) { }
        return true;
    }

    @Override
    public byte code() {
        return SLEEP;
    }

    @Override
    public int instLength() {
        return 4;
    }

    @Override
    public String instCode() {
        return "SLEEP";
    }
}
