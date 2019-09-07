package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class PopRegisterInstruction extends Instruction {

    @Override
    public final boolean run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        m.getRegister().setInteger(reg1, m.getStack().pop());
        return true;
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
