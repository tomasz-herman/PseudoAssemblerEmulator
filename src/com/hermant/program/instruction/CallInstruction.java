package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class CallInstruction extends Instruction implements JumpOperation, MemoryOperation {

    @Override
    public final void run(Machine m){
        final int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        m.getStack().push(m.getInstructionPointer().get() + instLength());
        jump(m.getInstructionPointer(), ramAddress);
    }

    @Override
    public final byte code() {
        return CALL;
    }

    @Override
    public final int instLength() {
        return 4;
    }

    @Override
    public final String instCode() {
        return "CALL";
    }
}