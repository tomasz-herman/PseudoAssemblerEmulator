package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class XorRegisterInstruction extends Instruction implements LogicalOperation {

    @Override
    public final boolean run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        m.getRegister().setInteger(reg1, logical(m.getRegister().getInteger(reg1) ^ m.getRegister().getInteger(reg2), m.getFlagsRegister()));
        return true;
    }

    @Override
    public final byte code() {
        return XOR_REGISTER;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "XOR_REGISTER";
    }
}