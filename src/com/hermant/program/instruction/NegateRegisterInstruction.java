package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class NegateRegisterInstruction extends Instruction {

    NegateRegisterInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.NEGATE_REGISTER, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int a = m.getRegister().getInteger(reg1);
        int result = -a;
        if(a == 0){
            m.getFlagsRegister().setCarryFlag();
            m.getFlagsRegister().setZeroFlag();
        }else {
            m.getFlagsRegister().resetCarryFlag();
            m.getFlagsRegister().resetZeroFlag();
        }
        if(a == Integer.MIN_VALUE) m.getFlagsRegister().setOverflowFlag();
        else m.getFlagsRegister().resetOverflowFlag();
        if((Integer.bitCount(result)&0x1)==0)m.getFlagsRegister().setParityFlag();
        else m.getFlagsRegister().resetParityFlag();
        m.getRegister().setInteger(reg1, result);
        return true;
    }

    @Override
    public String instCode() {
        return "NEGATE_REGISTER";
    }
}