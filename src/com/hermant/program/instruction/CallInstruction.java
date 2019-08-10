package com.hermant.program.instruction;

import com.hermant.machine.Machine;

import static com.hermant.machine.Register.INSTRUCTION_POINTER;

public class CallInstruction extends Instruction implements JumpOperation {

    CallInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.CALL, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister());
        m.getStack().push(m.getRegister().getInteger(INSTRUCTION_POINTER));
        jump(m.getRegister(), ramAddress);
        return true;
    }

    @Override
    public String instCode() {
        return "CALL";
    }
}