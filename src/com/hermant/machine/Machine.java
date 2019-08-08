package com.hermant.machine;

import com.hermant.program.Instruction;
import com.hermant.program.Program;

import java.util.Random;

import static com.hermant.machine.RandomAccessMemory.Endianness.*;
import static com.hermant.machine.Register.*;

public class Machine {

    private Register register;
    private Register floatingPointRegister;
    private FlagsRegister flagsRegister;
    private RandomAccessMemory ram;
    private Stack stack;
    public static final int SECTION_SIZE = 65536;
    private boolean debug;

    public Machine(boolean debug){
        register = new Register();
        floatingPointRegister = new Register();
        flagsRegister = new FlagsRegister();
        ram = new RandomAccessMemory(LittleEndian);
        stack = new Stack(ram, register);
        this.debug = debug;
        setupRegisterAddresses();
    }

    private void setupRegisterAddresses(){
        Random random = new Random();
        int programSection = random.nextInt(SECTION_SIZE / 4) * 4 + SECTION_SIZE * 4;
        int dataSection = programSection + random.nextInt(SECTION_SIZE / 4) * 4 + SECTION_SIZE;
        int extendedDataSection = dataSection + random.nextInt(SECTION_SIZE / 4) * 4 + SECTION_SIZE;
        int stackSection = extendedDataSection + random.nextInt(SECTION_SIZE / 4) * 4 + SECTION_SIZE;
        register.setInteger(REMAINDER, 0);
        register.setInteger(PROGRAM_SECTION, programSection);
        register.setInteger(DATA_SECTION, dataSection);
        register.setInteger(INSTRUCTION_POINTER, programSection);
        register.setInteger(EXTRA_DATA_SECTION, extendedDataSection);
        register.setInteger(STACK_SECTION, stackSection);
        register.setInteger(STACK_POINTER, stackSection + SECTION_SIZE);
        register.setInteger(STACK_FRAME_POINTER, stackSection + SECTION_SIZE);
    }

    public void loadProgram(Program program){
        int programPointer = register.getInteger(PROGRAM_SECTION);
        int dataPointer = register.getInteger(DATA_SECTION);
        for (var declaration : program.declarations)
            dataPointer=declaration.declare(ram, dataPointer);
        for (var instruction : program.instructions)
            programPointer=instruction.loadIntoMemory(ram, programPointer);
    }

    public void runProgram(){
        Instruction instruction = new Instruction(ram, register);
        while(instruction.execute(ram, register, floatingPointRegister, flagsRegister, stack, debug))
            instruction.loadFromMemory(ram, register);
    }

    @SuppressWarnings("unused")
    private void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

