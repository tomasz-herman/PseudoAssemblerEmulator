package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class PopInstruction extends Instruction implements MemoryOperation {

    PopInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(POP, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        m.getRam().setInteger(ramAddress, m.getStack().pop());
        return true;
    }

    @Override
    public int instLength() {
        return 4;
    }

    @Override
    public String instCode() {
        return "POP";
    }
}
