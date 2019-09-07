package com.hermant.program.instruction;

import com.hermant.machine.*;

public class ReturnInstruction extends Instruction implements JumpOperation{

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        jump(m.getInstructionPointer(), m.getStack().pop());
        return true;
    }

    @Override
    public byte code() {
        return RETURN;
    }

    @Override
    public int instLength() {
        return 2;
    }

    @Override
    public String instCode() {
        return "RETURN";
    }
}
