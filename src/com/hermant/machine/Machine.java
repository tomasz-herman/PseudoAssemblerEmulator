package com.hermant.machine;

import com.hermant.machine.ram.*;
import com.hermant.machine.register.FlagsRegister;
import com.hermant.machine.register.InstructionPointer;
import com.hermant.machine.register.Register;
import com.hermant.program.Program;
import com.hermant.program.instruction.InstructionFactory;
import sun.misc.Signal;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.IntConsumer;

import static com.hermant.machine.register.Register.*;

public class Machine {

    private Register register;
    private Register floatingPointRegister;
    private FlagsRegister flagsRegister;
    private RandomAccessMemory ram;
    private RandomNumberGenerator rng;
    private Stack stack;
    private InstructionPointer instructionPointer;
    public static final int SECTION_SIZE = 65536;
    private static final int RAM_SIZE = SECTION_SIZE * 64;
    private boolean debug;
    private long executedCounter = 0;

    public Machine(boolean debug){
        register = new Register();
        floatingPointRegister = new Register();
        flagsRegister = new FlagsRegister();
        ram = new ArrayRAM(Endianness.LittleEndian, RAM_SIZE);
        rng = new RandomNumberGenerator();
        stack = new Stack(ram, register);
        this.debug = debug;
    }

    private void setupRegisterAddresses(){
        Random random = new Random();
        int programSection = random.nextInt(4) * SECTION_SIZE;
        int dataSection = programSection + (1 + random.nextInt(3)) * SECTION_SIZE;
        int extendedDataSection = dataSection + (1 + random.nextInt(3)) * SECTION_SIZE;
        int stackSection = extendedDataSection + (1 + random.nextInt(3)) * SECTION_SIZE;
        register.setInteger(REMAINDER, 0);
        register.setInteger(POINTER, 0);
        register.setInteger(PROGRAM_SECTION, programSection);
        register.setInteger(DATA_SECTION, dataSection);
        register.setInteger(EXTRA_DATA_SECTION, extendedDataSection);
        register.setInteger(STACK_SECTION, stackSection);
        register.setInteger(STACK_POINTER, stackSection);
        register.setInteger(STACK_FRAME_POINTER, stackSection);
    }

    public void loadProgram(Program program){
        setupRegisterAddresses();
        int programPointer = register.getInteger(PROGRAM_SECTION);
        int dataPointer = register.getInteger(DATA_SECTION);
        for (var declaration : program.declarations)
            dataPointer=declaration.declare(ram, dataPointer);
        for (var instruction : program.instructions)
            programPointer=instruction.loadIntoMemory(ram, programPointer);
        instructionPointer = new InstructionPointer(register.getInteger(PROGRAM_SECTION));
        register.setInteger(POINTER, dataPointer);
    }

    public void runProgram(int sleep){
        IntConsumer next = (i) -> executedCounter++;
        if(sleep > 0) next = next.andThen(this::sleep);
        var running = new AtomicBoolean(true);
        Signal.handle(new Signal("INT"), sig -> running.set(false));
        final long start = System.nanoTime();
        while(running.get() && InstructionFactory.fetchNextInstruction(ram, instructionPointer).execute(this, debug))
            next.accept(sleep);
        final long millis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);
        System.out.println();
        System.out.println("Executed " + Long.toUnsignedString(executedCounter) + " instructions in " + millis + "ms.");
        executedCounter = 0;
    }

    private void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Register getRegister() {
        return register;
    }

    public Register getFPR() {
        return floatingPointRegister;
    }

    public FlagsRegister getFlagsRegister() {
        return flagsRegister;
    }

    public RandomAccessMemory getRam() {
        return ram;
    }

    public RandomNumberGenerator getRng() {
        return rng;
    }

    public Stack getStack() {
        return stack;
    }

    public InstructionPointer getInstructionPointer(){
        return instructionPointer;
    }

}

