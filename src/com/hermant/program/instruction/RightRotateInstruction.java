package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class RightRotateInstruction extends Instruction implements LogicalOperation {

    RightRotateInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.RIGHT_ROTATE, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister());
        m.getRegister().setInteger(reg1, setFlagsAfterLogicalOp(Integer.rotateRight(m.getRegister().getInteger(reg1), m.getRam().getInteger(ramAddress)), m.getFlagsRegister()));
        return true;
    }

    @Override
    public String instCode() {
        return "RIGHT_ROTATE";
    }
}