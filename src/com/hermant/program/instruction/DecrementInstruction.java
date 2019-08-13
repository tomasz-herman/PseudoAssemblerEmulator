package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class DecrementInstruction extends Instruction implements MemoryOperation, IntegerArithmeticOperation {

    DecrementInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(Instruction.DECREMENT, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        int a = m.getRam().getInteger(ramAddress);
        m.getRam().setInteger(ramAddress, decrement(a, m.getFlagsRegister()));
        return true;
    }

    @Override
    public String instCode() {
        return "DECREMENT";
    }
}