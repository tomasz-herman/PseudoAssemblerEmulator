package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class IncrementRegisterInstruction extends Instruction {

    IncrementRegisterInstruction(Byte reg1, Byte reg2, Short ramAddress) {
        super(Instruction.INCREMENT_REGISTER, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int a = m.getRegister().getInteger(reg1);
        int result = a + 1;
        if(result == 0) m.getFlagsRegister().setZeroFlag();
        else m.getFlagsRegister().resetZeroFlag();
        if(a == Integer.MAX_VALUE) m.getFlagsRegister().setOverflowFlag();
        else m.getFlagsRegister().resetOverflowFlag();
        if((Integer.bitCount(result)&0x1)==0)m.getFlagsRegister().setParityFlag();
        else m.getFlagsRegister().resetParityFlag();
        m.getRegister().setInteger(reg1, result);
        return true;
    }

    @Override
    public String instCode() {
        return "INCREMENT_REGISTER";
    }
}