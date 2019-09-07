package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class CompareInstruction extends Instruction implements MemoryOperation, IntegerArithmeticOperation {

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        int a = m.getRegister().getInteger(reg1);
        int b = m.getRam().getInteger(ramAddress);
        compare(a, b, m.getFlagsRegister(), (x, y) -> x - y);
        return true;
    }

    @Override
    public byte code() {
        return COMPARE;
    }

    @Override
    public int instLength() {
        return 4;
    }

    @Override
    public String instCode() {
        return "COMPARE";
    }
}