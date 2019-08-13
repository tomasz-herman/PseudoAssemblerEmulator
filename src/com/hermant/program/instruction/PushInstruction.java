package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class PushInstruction extends Instruction implements MemoryOperation {

    PushInstruction(Byte reg1, Byte reg2, Short ramAddress) {
        super(Instruction.PUSH, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        m.getStack().push(m.getRam().getInteger(ramAddress));
        return true;
    }

    @Override
    public String instCode() {
        return "PUSH";
    }
}
