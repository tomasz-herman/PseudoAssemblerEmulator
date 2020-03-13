package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class ExchangeFloatInstruction extends Instruction implements ExchangeOperation, MemoryOperation {

    @Override
    public final void run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        final int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        exchangeFloatRegMem(m.getFPR(), reg1, m.getRam(), ramAddress);
    }

    @Override
    public final byte code() {
        return EXCHANGE_FLOAT;
    }

    @Override
    public final int instLength() {
        return 4;
    }

    @Override
    public final String instCode() {
        return "EXCHANGE_FLOAT";
    }
}