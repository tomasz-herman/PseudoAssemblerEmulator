package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class StoreByteInstruction extends Instruction {

    StoreByteInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.STORE_BYTE, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister());
        m.getRam().setByte(ramAddress, (byte)m.getRegister().getInteger(reg1));
        return true;
    }
}