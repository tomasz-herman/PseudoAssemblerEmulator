package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class LoopInstruction extends Instruction implements JumpOperation {

    @Override
    public final boolean execute(Machine m, boolean debug){
        if(debug) debug(m.getInstructionPointer());
        final int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        m.getRegister().setInteger(reg1, m.getRegister().getInteger(reg1) - 1);
        if(m.getRegister().getInteger(reg1)!=0) jump(m.getInstructionPointer(), ramAddress);
        else setInstructionPointer(m.getInstructionPointer());
        return true;
    }

    @Override
    public final byte code() {
        return LOOP;
    }

    @Override
    public final int instLength() {
        return 4;
    }

    @Override
    public final String instCode() {
        return "LOOP";
    }
}