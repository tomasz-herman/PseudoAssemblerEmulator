package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class ExchangeFloatInstruction extends Instruction implements ExchangeOperation {

    ExchangeFloatInstruction(Byte reg1, Byte reg2, Short ramAddress) {
        super(Instruction.EXCHANGE_FLOAT, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        exchangeRegMem(m.getFPR(), reg1, m.getRam(), ramAddress);
        return true;
    }

    @Override
    public String instCode() {
        return "EXCHANGE_FLOAT";
    }
}