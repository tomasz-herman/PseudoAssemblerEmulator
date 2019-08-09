package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class ExchangeFloatInstruction extends Instruction {

    ExchangeFloatInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.EXCHANGE_FLOAT, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister());
        exchangeRegMem(m.getFPR(), m.getRam(), ramAddress);
        return true;
    }
}