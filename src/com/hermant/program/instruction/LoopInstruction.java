package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class LoopInstruction extends Instruction implements JumpOperation {

    @Override
    public final boolean run(Machine m){
        final int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        m.getRegister().set(reg1, m.getRegister().get(reg1) - 1);
        if(m.getRegister().get(reg1)!=0) jump(m.getInstructionPointer(), ramAddress);
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