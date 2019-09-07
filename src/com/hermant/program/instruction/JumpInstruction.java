package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class JumpInstruction extends Instruction implements JumpOperation {

    @Override
    public final boolean execute(Machine m, boolean debug){
        if(debug) debug(m.getInstructionPointer());
        final int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        jump(m.getInstructionPointer(), ramAddress);
        return true;
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