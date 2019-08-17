package com.hermant.program.instruction;

import com.hermant.machine.Machine;

import static com.hermant.machine.register.Register.REMAINDER;

public class DivideSignedRegisterInstruction extends Instruction implements IntegerArithmeticOperation {

    DivideSignedRegisterInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(Instruction.DIVIDE_SIGNED_REGISTER, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int b = m.getRegister().getInteger(reg2);
        int a = m.getRegister().getInteger(reg1);
        DivisionResult result = divideSigned(a, b, m.getFlagsRegister());
        m.getRegister().setInteger(reg1, result.result);
        m.getRegister().setInteger(REMAINDER, result.remainder);
        return true;
    }

    @Override
    public int instLength() {
        return 2;
    }

    @Override
    public String instCode() {
        return "DIVIDE_SIGNED_REGISTER";
    }
}