package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class PopRegisterFloatInstruction extends Instruction {

    @Override
    public final void run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        m.getFPR().set(reg1, m.getStack().popFloat());
    }

    @Override
    public final byte code() {
        return POP_REGISTER_FLOAT;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "POP_REGISTER_FLOAT";
    }
}
