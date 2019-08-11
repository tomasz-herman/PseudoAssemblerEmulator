package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class CallInstruction extends Instruction implements JumpOperation {

    CallInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.CALL, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister());
        m.getStack().push(m.getInstructionPointer().get());
        jump(m.getInstructionPointer(), ramAddress);
        return true;
    }

    @Override
    public String instCode() {
        return "CALL";
    }
}