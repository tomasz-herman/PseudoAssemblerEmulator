package com.hermant.machine.register;

public class InstructionPointer {

    public InstructionPointer(int instructionPointer) {
        this.instructionPointer = instructionPointer;
    }

    private int instructionPointer;

    public int get(){
        return instructionPointer;
    }

    public void set(int instructionPointer){
        this.instructionPointer = instructionPointer;
    }
}
