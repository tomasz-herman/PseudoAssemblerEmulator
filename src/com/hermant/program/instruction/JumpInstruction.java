package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class JumpInstruction extends Instruction {

    JumpInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.JUMP, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister());
        jump(m.getRegister(), ramAddress);
        return true;
    }
}