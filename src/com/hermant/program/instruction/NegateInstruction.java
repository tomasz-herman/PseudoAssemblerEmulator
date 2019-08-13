package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class NegateInstruction extends Instruction implements MemoryOperation {

    NegateInstruction(Byte reg1, Byte reg2, Short ramAddress) {
        super(Instruction.NEGATE, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        int a = m.getRam().getInteger(ramAddress);
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
        m.getRam().setInteger(ramAddress, result);
        return true;
    }

    @Override
    public String instCode() {
        return "NEGATE";
    }
}