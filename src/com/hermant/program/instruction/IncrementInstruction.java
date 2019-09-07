package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class IncrementInstruction extends Instruction implements MemoryOperation, IntegerArithmeticOperation {

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        int a = m.getRam().getInteger(ramAddress);
        m.getRam().setInteger(ramAddress, increment(a, m.getFlagsRegister()));
        return true;
    }

    @Override
    public byte code() {
        return INCREMENT;
    }

    @Override
    public int instLength() {
        return 4;
    }

    @Override
    public String instCode() {
        return "INCREMENT";
    }
}