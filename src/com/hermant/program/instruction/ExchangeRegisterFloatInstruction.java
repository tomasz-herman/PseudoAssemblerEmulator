package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class ExchangeRegisterFloatInstruction extends Instruction {

    ExchangeRegisterFloatInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.EXAMINE_FLOAT_REGISTER, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        exchangeRegReg(m.getFPR());
        return true;
    }
}