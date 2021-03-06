package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class IncrementInstruction extends Instruction implements MemoryOperation, IntegerArithmeticOperation {

    @Override
    public final void run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        final int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        int a = m.getRam().getInteger(ramAddress);
        m.getRam().setInteger(ramAddress, increment(a, m.getFlagsRegister()));
    }

    @Override
    public final byte code() {
        return INCREMENT;
    }

    @Override
    public final int instLength() {
        return 4;
    }

    @Override
    public final String instCode() {
        return "INCREMENT";
    }
}