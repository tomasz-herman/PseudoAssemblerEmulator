package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class SubtractFloatInstruction extends Instruction implements MemoryOperation {

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        m.getFPR().setFloat(reg1, m.getFPR().getFloat(reg1) - m.getRam().getFloat(ramAddress));
        return true;
    }

    @Override
    public byte code() {
        return SUBTRACT_FLOAT;
    }

    @Override
    public int instLength() {
        return 4;
    }

    @Override
    public String instCode() {
        return "SUBTRACT_FLOAT";
    }
}