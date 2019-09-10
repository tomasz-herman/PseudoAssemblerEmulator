package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class RightShiftLogicalRegisterInstruction extends Instruction implements LogicalOperation {

    @Override
    public final void run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        m.getRegister().set(reg1, logical(m.getRegister().get(reg1) >>> m.getRegister().get(reg2), m.getFlagsRegister()));
    }

    @Override
    public final byte code() {
        return RIGHT_SHIFT_LOGICAL_REGISTER;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "RIGHT_SHIFT_LOGICAL_REGISTER";
    }
}