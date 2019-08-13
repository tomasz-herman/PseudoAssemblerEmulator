package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class ExchangeRegisterFloatInstruction extends Instruction implements ExchangeOperation {

    ExchangeRegisterFloatInstruction(Byte reg1, Byte reg2, Short ramAddress) {
        super(Instruction.EXAMINE_FLOAT_REGISTER, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        exchangeRegReg(m.getFPR(), reg1, reg2);
        return true;
    }

    @Override
    public String instCode() {
        return "EXAMINE_FLOAT_REGISTER";
    }
}