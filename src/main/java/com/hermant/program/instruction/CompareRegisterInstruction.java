package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class CompareRegisterInstruction extends Instruction implements IntegerArithmeticOperation {

    @Override
    public final void run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        int a = m.getRegister().get(reg1);
        int b = m.getRegister().get(reg2);
        compare(a, b, m.getFlagsRegister(), (x, y) -> x - y);
    }

    @Override
    public final byte code() {
        return COMPARE_REGISTER;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "COMPARE_REGISTER";
    }
}