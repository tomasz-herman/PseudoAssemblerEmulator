package com.hermant.program.instruction;

import com.hermant.machine.Register;

import static com.hermant.machine.Register.INSTRUCTION_POINTER;

public interface JumpOperation {

    default  void jump(Register reg, int ramAddress){
        reg.setInteger(INSTRUCTION_POINTER, ramAddress);
    }

}
