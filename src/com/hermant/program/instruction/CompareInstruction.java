package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class CompareInstruction extends Instruction implements MemoryOperation, IntegerArithmeticOperation {

    @Override
    public final void run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        final int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        int a = m.getRegister().get(reg1);
        int b = m.getRam().getInteger(ramAddress);
        compare(a, b, m.getFlagsRegister(), (x, y) -> x - y);
    }

    @Override
    public final byte code() {
        return COMPARE;
    }

    @Override
    public final int instLength() {
        return 4;
    }

    @Override
    public final String instCode() {
        return "COMPARE";
    }
}