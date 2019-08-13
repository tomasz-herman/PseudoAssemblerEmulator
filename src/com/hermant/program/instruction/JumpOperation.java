package com.hermant.program.instruction;

import com.hermant.machine.register.InstructionPointer;

public interface JumpOperation extends MemoryOperation{

    default  void jump(InstructionPointer instructionPointer, int ramAddress){
        instructionPointer.set(ramAddress);
    }

}
