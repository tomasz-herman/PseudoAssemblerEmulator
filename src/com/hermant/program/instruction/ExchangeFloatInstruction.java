package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class ExchangeFloatInstruction extends Instruction implements ExchangeOperation, MemoryOperation {

    ExchangeFloatInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(EXCHANGE_FLOAT, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        exchangeRegMem(m.getFPR(), reg1, m.getRam(), ramAddress);
        return true;
    }

    @Override
    public int instLength() {
        return 4;
    }

    @Override
    public String instCode() {
        return "EXCHANGE_FLOAT";
    }
}