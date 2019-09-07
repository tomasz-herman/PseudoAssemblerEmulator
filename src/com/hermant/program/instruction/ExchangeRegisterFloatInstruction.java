package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class ExchangeRegisterFloatInstruction extends Instruction implements ExchangeOperation {

    @Override
    public final boolean execute(Machine m, boolean debug){
        if(debug) debug(m.getInstructionPointer());
        setInstructionPointer(m.getInstructionPointer());
        exchangeRegReg(m.getFPR(), reg1, reg2);
        return true;
    }

    @Override
    public final byte code() {
        return EXAMINE_FLOAT_REGISTER;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "EXAMINE_FLOAT_REGISTER";
    }
}