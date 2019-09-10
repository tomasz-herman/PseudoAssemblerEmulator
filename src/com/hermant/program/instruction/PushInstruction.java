package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class PushInstruction extends Instruction implements MemoryOperation {

    @Override
    public final void run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        final int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        m.getStack().push(m.getRam().getInteger(ramAddress));
    }

    @Override
    public final byte code() {
        return PUSH;
    }

    @Override
    public final int instLength() {
        return 4;
    }

    @Override
    public final String instCode() {
        return "PUSH";
    }
}
