package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class ExchangeRegisterInstruction extends Instruction {

    ExchangeRegisterInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.EXCHANGE_REGISTER, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        exchangeRegReg(m.getRegister());
        return true;
    }
}