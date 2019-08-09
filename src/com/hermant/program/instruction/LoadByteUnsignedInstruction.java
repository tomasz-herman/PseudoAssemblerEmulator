package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class LoadByteUnsignedInstruction extends Instruction {

    LoadByteUnsignedInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.LOAD_BYTE_UNSIGNED, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister());
        m.getRegister().setInteger(reg1, m.getRam().getByte(ramAddress));
        return true;
    }
}