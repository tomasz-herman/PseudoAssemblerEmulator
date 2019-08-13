package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class LoadAddressInstruction extends Instruction implements MemoryOperation {

    LoadAddressInstruction(Byte reg1, Byte reg2, Short ramAddress) {
        super(Instruction.LOAD_ADDRESS, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        m.getRegister().setInteger(reg1, ramAddress);
        return true;
    }

    @Override
    public String instCode() {
        return "LOAD_ADDRESS";
    }
}
