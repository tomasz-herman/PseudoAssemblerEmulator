package com.hermant.program.instruction;

import com.hermant.machine.*;

public class ReturnInstruction extends Instruction implements JumpOperation {

    @Override
    public final void run(Machine m){
        jump(m.getInstructionPointer(), m.getStack().pop());
    }

    @Override
    public final byte code() {
        return RETURN;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "RETURN";
    }
}
