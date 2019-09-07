package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class CallInstruction extends Instruction implements JumpOperation {

    @Override
    public final boolean execute(Machine m, boolean debug){
        if(debug) debug(m.getInstructionPointer());
        final int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        m.getStack().push(m.getInstructionPointer().get() + instLength());
        jump(m.getInstructionPointer(), ramAddress);
        return true;
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