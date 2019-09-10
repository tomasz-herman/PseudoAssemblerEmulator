package com.hermant.program.instruction;

import com.hermant.machine.Machine;
import com.hermant.machine.register.GeneralPurposeRegister;

public class TimeInstruction extends Instruction implements MemoryOperation {

    @Override
    public final void run(Machine m) {
        setInstructionPointer(m.getInstructionPointer());
        final int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        long timeMillis = System.currentTimeMillis();
        m.getRam().setInteger(ramAddress, (int)(timeMillis / 1000));
        m.getRegister().set(GeneralPurposeRegister.REMAINDER, (int) Long.remainderUnsigned(timeMillis, 1000));
    }

    @Override
    public final byte code() {
        return TIME;
    }

    @Override
    public final int instLength() {
        return 4;
    }

    @Override
    public final String instCode() {
        return "TIME";
    }
}
