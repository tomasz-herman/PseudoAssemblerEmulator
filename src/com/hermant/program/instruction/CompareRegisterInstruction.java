package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class CompareRegisterInstruction extends Instruction implements IntegerArithmeticOperation {

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int a = m.getRegister().getInteger(reg1);
        int b = m.getRegister().getInteger(reg2);
        compare(a, b, m.getFlagsRegister(), (x, y) -> x - y);
        return true;
    }

    @Override
    public byte code() {
        return COMPARE_REGISTER;
    }

    @Override
    public int instLength() {
        return 2;
    }

    @Override
    public String instCode() {
        return "COMPARE_REGISTER";
    }
}