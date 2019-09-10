package com.hermant.program.instruction;

import com.hermant.machine.Machine;

import static com.hermant.machine.register.GeneralPurposeRegister.REMAINDER;

public class DivideRegisterInstruction extends Instruction implements IntegerArithmeticOperation {

    @Override
    public final boolean run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        int b = m.getRegister().get(reg2);
        int a = m.getRegister().get(reg1);
        DivisionResult result = divide(a, b, m.getFlagsRegister());
        m.getRegister().set(reg1, result.result);
        m.getRegister().set(REMAINDER, result.remainder);
        return true;
    }

    @Override
    public final byte code() {
        return DIVIDE_REGISTER;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "DIVIDE_REGISTER";
    }
}