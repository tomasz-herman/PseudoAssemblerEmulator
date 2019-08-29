package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class JumpNotParityInstruction extends Instruction implements JumpOperation {

    JumpNotParityInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(JUMP_NOT_PARITY, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        if(m.getFlagsRegister().isParityOdd()) jump(m.getInstructionPointer(), ramAddress);
        return true;
    }

    @Override
    public int instLength() {
        return 4;
    }

    @Override
    public String instCode() {
        return "JUMP_NOT_PARITY";
    }
}