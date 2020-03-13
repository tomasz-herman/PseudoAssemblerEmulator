package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class JumpInstruction extends Instruction implements JumpOperation, MemoryOperation {

    @Override
    public final void run(Machine m){
        final int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        jump(m.getInstructionPointer(), ramAddress);
    }

    @Override
    public final byte code() {
        return JUMP;
    }

    @Override
    public final int instLength() {
        return 4;
    }

    @Override
    public final String instCode() {
        return "JUMP";
    }
}