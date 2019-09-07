package com.hermant.program.instruction;

import com.hermant.machine.*;

public class ReturnInstruction extends Instruction implements JumpOperation{

    @Override
    public final boolean execute(Machine m, boolean debug){
        if(debug) debug(m.getInstructionPointer());
        jump(m.getInstructionPointer(), m.getStack().pop());
        return true;
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
