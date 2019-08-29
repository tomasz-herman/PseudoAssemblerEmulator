package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class AddInstruction extends Instruction implements MemoryOperation, IntegerArithmeticOperation {

    AddInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(ADD, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        int a = m.getRegister().getInteger(reg1);
        int b = m.getRam().getInteger(ramAddress);
        m.getRegister().setInteger(reg1, add(a, b, m.getFlagsRegister()));
        return true;
    }

    @Override
    public int instLength() {
        return 4;
    }

    @Override
    public String instCode() {
        return "ADD";
    }
}