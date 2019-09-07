package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class ExchangeRegisterFloatInstruction extends Instruction implements ExchangeOperation {

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        exchangeRegReg(m.getFPR(), reg1, reg2);
        return true;
    }

    @Override
    public byte code() {
        return EXAMINE_FLOAT_REGISTER;
    }

    @Override
    public int instLength() {
        return 2;
    }

    @Override
    public String instCode() {
        return "EXAMINE_FLOAT_REGISTER";
    }
}