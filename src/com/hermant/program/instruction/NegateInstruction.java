package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class NegateInstruction extends Instruction implements MemoryOperation, IntegerArithmeticOperation {

    @Override
    public final boolean execute(Machine m, boolean debug){
        if(debug) debug(m.getInstructionPointer());
        setInstructionPointer(m.getInstructionPointer());
        final int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        int a = m.getRam().getInteger(ramAddress);
        m.getRam().setInteger(ramAddress, negate(a, m.getFlagsRegister()));
        return true;
    }

    @Override
    public final byte code() {
        return NEGATE;
    }

    @Override
    public final int instLength() {
        return 4;
    }

    @Override
    public final String instCode() {
        return "NEGATE";
    }
}