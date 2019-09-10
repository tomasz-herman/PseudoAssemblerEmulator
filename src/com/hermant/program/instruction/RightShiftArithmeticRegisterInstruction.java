package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class RightShiftArithmeticRegisterInstruction extends Instruction implements LogicalOperation {

    @Override
    public final boolean run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        m.getRegister().set(reg1, logical(m.getRegister().get(reg1) >> m.getRegister().get(reg2), m.getFlagsRegister()));
        return true;
    }

    @Override
    public final byte code() {
        return RIGHT_SHIFT_ARITHMETIC_REGISTER;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "RIGHT_SHIFT_ARITHMETIC_REGISTER";
    }
}