package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class StoreFloatInstruction extends Instruction implements MemoryOperation {

    @Override
    public final void run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        final int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        m.getRam().setFloat(ramAddress, m.getFPR().get(reg1));
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
