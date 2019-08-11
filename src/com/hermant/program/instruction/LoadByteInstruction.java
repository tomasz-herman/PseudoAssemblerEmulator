package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class LoadByteInstruction extends Instruction {

    LoadByteInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.LOAD_BYTE, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister());
        m.getRegister().setInteger(reg1, (byte)m.getRam().getByte(ramAddress));
        return true;
    }

    @Override
    public String instCode() {
        return "LOAD_BYTE";
    }
}