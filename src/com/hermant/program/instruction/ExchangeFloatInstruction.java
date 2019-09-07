package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class ExchangeFloatInstruction extends Instruction implements ExchangeOperation, MemoryOperation {

    @Override
    public final boolean execute(Machine m, boolean debug){
        if(debug) debug(m.getInstructionPointer());
        setInstructionPointer(m.getInstructionPointer());
        final int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        exchangeRegMem(m.getFPR(), reg1, m.getRam(), ramAddress);
        return true;
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