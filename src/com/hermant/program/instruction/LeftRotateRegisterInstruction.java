package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class LeftRotateRegisterInstruction extends Instruction implements LogicalOperation {

    @Override
    public final void run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        m.getRegister().set(reg1, logical(Integer.rotateLeft(m.getRegister().get(reg1), m.getRegister().get(reg2)), m.getFlagsRegister()));
    }

    @Override
    public final byte code() {
        return LEFT_ROTATE_REGISTER;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "LEFT_ROTATE_REGISTER";
    }
}