package com.hermant.program.instruction;

import com.hermant.machine.Machine;
import com.hermant.machine.register.Register;

public class TimeInstruction extends Instruction implements MemoryOperation {

    @Override
    public boolean execute(Machine m, boolean debug) {
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        long timeMillis = System.currentTimeMillis();
        m.getRam().setInteger(ramAddress, (int)(timeMillis / 1000));
        m.getRegister().setInteger(Register.REMAINDER, (int) Long.remainderUnsigned(timeMillis, 1000));
        return true;
    }

    @Override
    public byte code() {
        return TIME;
    }

    @Override
    public int instLength() {
        return 4;
    }

    @Override
    public String instCode() {
        return "TIME";
    }
}
