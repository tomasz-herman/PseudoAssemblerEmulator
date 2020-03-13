package com.hermant.program.instruction;

import com.hermant.machine.Machine;

import static com.hermant.machine.register.GeneralPurposeRegister.REMAINDER;

public class DivideSignedRegisterInstruction extends Instruction implements IntegerArithmeticOperation {

    @Override
    public final void run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        int b = m.getRegister().get(reg2);
        int a = m.getRegister().get(reg1);
        DivisionResult result = divideSigned(a, b, m.getFlagsRegister());
        m.getRegister().set(reg1, result.result);
        m.getRegister().set(REMAINDER, result.remainder);
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