package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class NegateInstruction extends Instruction implements MemoryOperation, IntegerArithmeticOperation {

    NegateInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(NEGATE, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        int a = m.getRam().getInteger(ramAddress);
        m.getRam().setInteger(ramAddress, negate(a, m.getFlagsRegister()));
        return true;
    }

    @Override
    public int instLength() {
        return 4;
    }

    @Override
    public String instCode() {
        return "NEGATE";
    }
}