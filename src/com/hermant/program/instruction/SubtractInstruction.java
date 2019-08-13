package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class SubtractInstruction extends Instruction implements MemoryOperation {

    SubtractInstruction(Byte reg1, Byte reg2, Short ramAddress) {
        super(Instruction.SUBTRACT, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        int a = m.getRegister().getInteger(reg1);
        int b = m.getRam().getInteger(ramAddress);
        compare(a, b, m.getFlagsRegister(), (x, y) -> x - y);
        m.getRegister().setInteger(reg1, a - b);
        return true;
    }

    @Override
    public String instCode() {
        return "SUBTRACT";
    }
}