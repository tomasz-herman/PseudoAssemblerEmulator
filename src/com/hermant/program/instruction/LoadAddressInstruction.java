package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class LoadAddressInstruction extends Instruction implements MemoryOperation {

    @Override
    public final boolean execute(Machine m, boolean debug){
        if(debug) debug(m.getInstructionPointer());
        setInstructionPointer(m.getInstructionPointer());
        final int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        m.getRegister().setInteger(reg1, ramAddress);
        return true;
    }

    @Override
    public final byte code() {
        return LOAD_ADDRESS;
    }

    @Override
    public final int instLength() {
        return 4;
    }

    @Override
    public final String instCode() {
        return "LOAD_ADDRESS";
    }
}
