package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class SubtractRegisterInstruction extends Instruction implements IntegerArithmeticOperation {

    @Override
    public final boolean execute(Machine m, boolean debug){
        if(debug) debug(m.getInstructionPointer());
        setInstructionPointer(m.getInstructionPointer());
        int a = m.getRegister().getInteger(reg1);
        int b = m.getRegister().getInteger(reg2);
        m.getRegister().setInteger(reg1, subtract(a, b, m.getFlagsRegister()));
        return true;
    }

    @Override
    public final byte code() {
        return SUBTRACT_REGISTER;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "SUBTRACT_REGISTER";
    }
}