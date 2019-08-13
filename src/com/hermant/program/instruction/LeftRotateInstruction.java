package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class LeftRotateInstruction extends Instruction implements LogicalOperation, MemoryOperation {

    LeftRotateInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(Instruction.LEFT_ROTATE, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        m.getRegister().setInteger(reg1, logical(Integer.rotateLeft(m.getRegister().getInteger(reg1), m.getRam().getInteger(ramAddress)), m.getFlagsRegister()));
        return true;
    }

    @Override
    public String instCode() {
        return "LEFT_ROTATE";
    }
}