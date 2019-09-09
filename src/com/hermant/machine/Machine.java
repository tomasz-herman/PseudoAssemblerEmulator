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

/**
 * {@link Machine} contains General Purpose {@link Register}(GPR), Floating Point {@link Register}(FPR),
 * {@link FlagsRegister}, {@link RandomAccessMemory}, {@link RandomNumberGenerator}, {@link InstructionPointer}
 * and {@link Stack}. {@link Program} might be loaded({@link Machine#loadProgram(Program)}) into {@link Machine#ram}
 * and started({@link Machine#runProgram(int sleep)}). To create a new {@link Machine} see constructor:
 * {@link Machine#Machine(boolean debug, boolean unsafe)}.
 */
public class Machine {

    /**
     * General Purpose {@link Register}(GPR) used for storing ints and addressing.
     */
    private Register register;
    /**
     * Floating Point {@link Register}(FPR) used for storing floats.
     */
    private Register floatingPointRegister;
    /**
     * {@link FlagsRegister} indicating status of last operation that affects flags. These are:
     * {@link com.hermant.program.instruction.IntegerArithmeticOperation IntegerArithmeticOperation},
     * {@link com.hermant.program.instruction.LogicalOperation LogicalOperation} and
     * {@link com.hermant.program.instruction.FloatArithmeticOperation FloatArithmeticOperation}.
     */
    private FlagsRegister flagsRegister;
    /**
     * {@link RandomAccessMemory} used for storing data that machine needs for operating.
     */
    private RandomAccessMemory ram;
    /**
     * {@link RandomNumberGenerator} used as a source of entropy.
     */
    private RandomNumberGenerator rng;
    /**
     * Section of ram used as {@link Stack}.
     */
    private Stack stack;
    /**
     * {@link InstructionPointer} stores pointer to next Instruction that should be executed.
     */
    private InstructionPointer instructionPointer;
    /**
     * Defines section size which is equal to 2^16(64kB).
     */
    public static final int SECTION_SIZE = 65536;
    /**
     * Default ram size is equal to 64*{@link Machine#SECTION_SIZE}=2^22(4MB).
     */
    private static final int RAM_SIZE = SECTION_SIZE * 64;
    /**
     * If true debug info will be printed during execution of command at cost of performance.
     */
    private boolean debug;
    /**
     * Counts how many instructions were executed during run of a program.
     */
    private long executedCounter = 0;

    /**
     * Creates a new {@link Machine}. It contains General Purpose {@link Register}(GPR),
     * Floating Point {@link Register}(FPR), {@link FlagsRegister}, {@link RandomAccessMemory},
     * {@link RandomNumberGenerator}, {@link InstructionPointer} and {@link Stack}.
     * @param debug if executing command should print info. Will degrade performance if true.
     * @param unsafe if simulated ram should be protected from leaking into actual ram.
     */
    public Machine(boolean debug, boolean unsafe){
        register = new Register();
        floatingPointRegister = new Register();
        flagsRegister = new FlagsRegister();
        ram = unsafe ? new UnsafeRAM(RAM_SIZE) : new SafeRAM(RAM_SIZE);
        rng = new RandomNumberGenerator();
        instructionPointer = new InstructionPointer(0);
        stack = new Stack(ram, register);
        this.debug = debug;
    }

    /**
     * Allocates memory sections that might be used by running program. The sections are:
     * <p><ul>
     * <li>Program section
     * <li>Data section
     * <li>Extra data section
     * <li>Stack section
     * </ul><p>
     * Each section takes {@link Machine#SECTION_SIZE}(65536) bytes of ram.
     * Following registers are set:
     * <p><ul>
     * <li>{@link Register#REMAINDER} is set to 0
     * <li>{@link Register#POINTER} is set to 0
     * <li>{@link Register#PROGRAM_SECTION} is set to address of program section start
     * <li>{@link Register#DATA_SECTION} is set to address of data section start
     * <li>{@link Register#EXTRA_DATA_SECTION} is set to address of extra data section start
     * <li>{@link Register#STACK_SECTION} is set to address of stack section start
     * <li>{@link Register#STACK_POINTER} is set to address of stack section start
     * <li>{@link Register#STACK_FRAME_POINTER} is set to address of stack section start
     * </ul><p>
     */
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

    /**
     * Loads program into memory. Sections(program, data, extra data, stack) are allocated during the process
     * and General Purpose {@link Register} {@link Machine#register}s are set up accordingly. For more info
     * see {@link Machine#setupRegisterAddresses()}.
     * @param program is loaded into memory starting from address stored at {@link Register#PROGRAM_SECTION PROGRAM_SECTION}
     */
    public void loadProgram(Program program){
        setupRegisterAddresses();
        int programPointer = register.getInteger(PROGRAM_SECTION);
        int dataPointer = register.getInteger(DATA_SECTION);
        for (var declaration : program.declarations)
            dataPointer=declaration.declare(ram, dataPointer);
        if(debug) System.out.println("Program:");
        for (var instruction : program.instructions)
            programPointer=instruction.loadIntoMemory(debug, ram, programPointer);
        if(debug) System.out.println();
        instructionPointer.set(register.getInteger(PROGRAM_SECTION));
        register.setInteger(POINTER, dataPointer);
    }

    /**
     * Runs program starting from instruction at address stored in {@link InstructionPointer}.
     * Ends when reaches {@link com.hermant.program.instruction.ExitInstruction ExitInstruction} or
     * when unrecognizable instruction is loaded resulting in {@link IllegalStateException}.
     * @param sleep time in milliseconds to sleep in between instructions
     */
    public void runProgram(int sleep){
        System.out.println("Program has started");
        IntConsumer next = (i) -> executedCounter++;
        if(sleep > 0) next = next.andThen(this::sleep);
        var running = new AtomicBoolean(true);
        Thread t = Thread.currentThread();
        Signal.handle(new Signal("INT"), sig -> {
            running.set(false);
            t.interrupt();
        });
        final long start = System.nanoTime();
        if(debug)
            while(running.get() && InstructionFactory.fetchNextInstruction(ram, instructionPointer).debug(this))
                next.accept(sleep);
        else
            while(running.get() && InstructionFactory.fetchNextInstruction(ram, instructionPointer).run(this))
                next.accept(sleep);
        final long millis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);
        System.out.println();
        System.out.println("Executed " + Long.toUnsignedString(executedCounter) + " instructions in " + millis + "ms.");
        executedCounter = 0;
    }

    /**
     * Sleeps for specified number of milliseconds
     * @param millis time in milliseconds to sleep
     */
    private void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) { }
    }


    /**
     * Free unused resources
     */
    public void free(){
        ram.free();
    }

    /**
     * @return Machine's General Purpose {@link Register}:{@link Machine#register}
     */
    public Register getRegister() {
        return register;
    }

    /**
     * @return Machine's Floating Point {@link Register}:{@link Machine#floatingPointRegister}
     */
    public Register getFPR() {
        return floatingPointRegister;
    }

    /**
     * @return Machine's {@link FlagsRegister}:{@link Machine#flagsRegister}
     */
    public FlagsRegister getFlagsRegister() {
        return flagsRegister;
    }

    /**
     * @return Machine's {@link RandomAccessMemory}:{@link Machine#ram}
     */
    public RandomAccessMemory getRam() {
        return ram;
    }

    /**
     * @return Machine's {@link RandomNumberGenerator}:{@link Machine#rng}
     */
    public RandomNumberGenerator getRng() {
        return rng;
    }

    /**
     * @return Machine's {@link Stack}:{@link Machine#stack}
     */
    public Stack getStack() {
        return stack;
    }

    /**
     * @return Machine's {@link InstructionPointer}:{@link Machine#instructionPointer}
     */
    public InstructionPointer getInstructionPointer(){
        return instructionPointer;
    }

}

