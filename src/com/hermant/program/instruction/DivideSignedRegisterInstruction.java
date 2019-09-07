package com.hermant.program.instruction;

import com.hermant.machine.Machine;

import static com.hermant.machine.register.Register.REMAINDER;

public class DivideSignedRegisterInstruction extends Instruction implements IntegerArithmeticOperation {

    @Override
    public final boolean execute(Machine m, boolean debug){
        if(debug) debug(m.getInstructionPointer());
        setInstructionPointer(m.getInstructionPointer());
        int b = m.getRegister().getInteger(reg2);
        int a = m.getRegister().getInteger(reg1);
        DivisionResult result = divideSigned(a, b, m.getFlagsRegister());
        m.getRegister().setInteger(reg1, result.result);
        m.getRegister().setInteger(REMAINDER, result.remainder);
        return true;
    }

    @Override
    public final byte code() {
        return DIVIDE_SIGNED_REGISTER;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "DIVIDE_SIGNED_REGISTER";
    }
}