package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class RightShiftArithmeticRegisterInstruction extends Instruction implements LogicalOperation {

    @Override
    public final boolean execute(Machine m, boolean debug){
        if(debug) debug(m.getInstructionPointer());
        setInstructionPointer(m.getInstructionPointer());
        m.getRegister().setInteger(reg1, logical(m.getRegister().getInteger(reg1) >> m.getRegister().getInteger(reg2), m.getFlagsRegister()));
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