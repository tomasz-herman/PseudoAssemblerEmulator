package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class SleepInstruction extends Instruction implements MemoryOperation {

    SleepInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(SLEEP, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug) {
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        try {
            Thread.sleep(m.getRam().getInteger(ramAddress));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
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
