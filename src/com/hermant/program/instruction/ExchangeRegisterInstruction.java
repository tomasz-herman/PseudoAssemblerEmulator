package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class ExchangeRegisterInstruction extends Instruction implements ExchangeOperation {

    @Override
    public final void run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        exchangeRegReg(m.getRegister(), reg1, reg2);
    }

    @Override
    public final byte code() {
        return EXCHANGE_REGISTER;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "EXCHANGE_REGISTER";
    }
}