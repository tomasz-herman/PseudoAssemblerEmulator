package com.hermant.program.instruction;

import com.hermant.machine.Machine;

import static com.hermant.machine.register.Register.REMAINDER;

public class DivideRegisterInstruction extends Instruction implements IntegerArithmeticOperation {

    @Override
    public final boolean execute(Machine m, boolean debug){
        if(debug) debug(m.getInstructionPointer());
        setInstructionPointer(m.getInstructionPointer());
        int b = m.getRegister().getInteger(reg2);
        int a = m.getRegister().getInteger(reg1);
        DivisionResult result = divide(a, b, m.getFlagsRegister());
        m.getRegister().setInteger(reg1, result.result);
        m.getRegister().setInteger(REMAINDER, result.remainder);
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