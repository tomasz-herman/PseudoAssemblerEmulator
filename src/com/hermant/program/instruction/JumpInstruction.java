package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class JumpInstruction extends Instruction implements JumpOperation {

    JumpInstruction(Byte reg1, Byte reg2, Short ramAddress) {
        super(Instruction.JUMP, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        jump(m.getInstructionPointer(), ramAddress);
        return true;
    }

    @Override
    public String instCode() {
        return "JUMP";
    }
}