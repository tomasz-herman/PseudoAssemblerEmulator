package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class PushInstruction extends Instruction implements MemoryOperation {

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        m.getStack().push(m.getRam().getInteger(ramAddress));
        return true;
    }

    @Override
    public byte code() {
        return PUSH;
    }

    @Override
    public int instLength() {
        return 4;
    }

    @Override
    public String instCode() {
        return "PUSH";
    }
}
