package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class StoreFloatInstruction extends Instruction implements MemoryOperation {

    @Override
    public final boolean execute(Machine m, boolean debug){
        if(debug) debug(m.getInstructionPointer());
        setInstructionPointer(m.getInstructionPointer());
        final int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        m.getRam().setInteger(ramAddress, m.getFPR().getInteger(reg1));
        return true;
    }

    @Override
    public final byte code() {
        return STORE_FLOAT;
    }

    @Override
    public final int instLength() {
        return 4;
    }

    @Override
    public final String instCode() {
        return "STORE_FLOAT";
    }
}
