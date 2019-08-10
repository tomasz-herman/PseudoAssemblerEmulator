package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class PushInstruction extends Instruction {

    PushInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.PUSH, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister());
        m.getStack().push(m.getRam().getInteger(ramAddress));
        return true;
    }

    @Override
    public String instCode() {
        return "PUSH";
    }
}
