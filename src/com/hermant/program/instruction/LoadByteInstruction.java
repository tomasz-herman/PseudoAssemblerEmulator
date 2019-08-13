package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class LoadByteInstruction extends Instruction implements MemoryOperation {

    LoadByteInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(Instruction.LOAD_BYTE, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        m.getRegister().setInteger(reg1, m.getRam().getByte(ramAddress));
        return true;
    }

    @Override
    public String instCode() {
        return "LOAD_BYTE";
    }
}