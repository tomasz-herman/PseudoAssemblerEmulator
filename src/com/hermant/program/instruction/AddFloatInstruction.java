package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class AddFloatInstruction extends Instruction implements MemoryOperation {

    @Override
    public final boolean run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        final int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        m.getFPR().set(reg1, m.getFPR().get(reg1) + m.getRam().getFloat(ramAddress));
        return true;
    }

    @Override
    public final byte code() {
        return ADD_FLOAT;
    }

    @Override
    public final int instLength() {
        return 4;
    }

    @Override
    public final String instCode() {
        return "ADD_FLOAT";
    }
}