package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class ExchangeInstruction extends Instruction implements ExchangeOperation, MemoryOperation {

    ExchangeInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(Instruction.EXCHANGE, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        exchangeRegMem(m.getRegister(), reg1, m.getRam(), ramAddress);
        return true;
    }

    @Override
    public int instLength() {
        return 4;
    }

    @Override
    public String instCode() {
        return "EXCHANGE";
    }
}