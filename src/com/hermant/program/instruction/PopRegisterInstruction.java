package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class PopRegisterInstruction extends Instruction {

    @Override
    public final void run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        m.getRegister().set(reg1, m.getStack().pop());
    }

    @Override
    public final byte code() {
        return POP_REGISTER;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "POP_REGISTER";
    }
}
