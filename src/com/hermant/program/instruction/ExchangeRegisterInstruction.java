package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class ExchangeRegisterInstruction extends Instruction implements ExchangeOperation {

    ExchangeRegisterInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(Instruction.EXCHANGE_REGISTER, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        exchangeRegReg(m.getRegister(), reg1, reg2);
        return true;
    }

    @Override
    public int instLength() {
        return 2;
    }

    @Override
    public String instCode() {
        return "EXCHANGE_REGISTER";
    }
}