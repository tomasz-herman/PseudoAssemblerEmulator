package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class ExchangeInstruction extends Instruction {

    ExchangeInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.EXCHANGE, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister());
        exchangeRegMem(m.getRegister(), m.getRam(), ramAddress);
        return true;
    }
}