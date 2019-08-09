package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class DecrementInstruction extends Instruction {

    DecrementInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.DECREMENT, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister());
        int a = m.getRam().getInteger(ramAddress);
        int result = a - 1;
        if(result == 0) m.getFlagsRegister().setZeroFlag();
        else m.getFlagsRegister().resetZeroFlag();
        if(a == Integer.MIN_VALUE) m.getFlagsRegister().setOverflowFlag();
        else m.getFlagsRegister().resetOverflowFlag();
        if((Integer.bitCount(result)&0x1)==0)m.getFlagsRegister().setParityFlag();
        else m.getFlagsRegister().resetParityFlag();
        m.getRam().setInteger(ramAddress, result);
        return true;
    }
}