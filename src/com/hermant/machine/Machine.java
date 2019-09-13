package com.hermant.machine;

import com.hermant.machine.ram.*;
import com.hermant.machine.register.*;
import com.hermant.program.Program;
import com.hermant.program.instruction.InstructionUtils;
import sun.misc.Signal;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.hermant.machine.register.GeneralPurposeRegister.*;

/**
 * {@link Machine} contains General Purpose {@link GeneralPurposeRegister}(GPR), Floating Point {@link GeneralPurposeRegister}(FPR),
 * {@link FlagsRegister}, {@link RandomAccessMemory}, {@link RandomNumberGenerator}, {@link InstructionPointer}
 * and {@link Stack}. {@link Program} might be loaded({@link Machine#loadProgram(Program)}) into {@link Machine#ram}
 * and started({@link Machine#runProgram(int sleep)}). To create a new {@link Machine} see constructor:
 * {@link Machine#Machine(boolean debug, boolean unsafe)}.
 */
public class Machine {

    /**
     * General Purpose {@link GeneralPurposeRegister}(GPR) used for storing ints and addressing.
     */
    private final GeneralPurposeRegister register;
    /**
     * Floating Point {@link FloatingPointRegister}(FPR) used for storing floats.
     */
    private final FloatingPointRegister floatingPointRegister;
    /**
     * {@link FlagsRegister} indicating status of last operation that affects flags. These are:
     * {@link com.hermant.program.instruction.IntegerArithmeticOperation IntegerArithmeticOperation},
     * {@link com.hermant.program.instruction.LogicalOperation LogicalOperation} and
     * {@link com.hermant.program.instruction.FloatArithmeticOperation FloatArithmeticOperation}.
     */
    private final FlagsRegister flagsRegister;
    /**
     * {@link RandomAccessMemory} used for storing data that machine needs for operating.
     */
    private final RandomAccessMemory ram;
    /**
     * {@link RandomNumberGenerator} used as a source of entropy.
     */
    private final RandomNumberGenerator rng;
    /**
     * Section of ram used as {@link Stack}.
     */
    private final Stack stack;
    /**
     * {@link InstructionPointer} stores pointer to next Instruction that should be executed.
     */
    private final InstructionPointer instructionPointer;
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
    private final boolean debug;
    /**
     * Counts how many instructions were executed during run of a program.
     */
    private long executedCounter = 0;
    /**
     * Indicates if program is running.
     */
    private volatile boolean running = false;
    /**
     * Indicates if program is paused(only debug mode).
     */
    private volatile boolean paused = false;
    /**
     * For synchronizing pause.
     */
    private final Object MONITOR = new Object();

    /**
     * Creates a new {@link Machine}. It contains General Purpose {@link GeneralPurposeRegister}(GPR),
     * Floating Point {@link FloatingPointRegister}(FPR), {@link FlagsRegister}, {@link RandomAccessMemory},
     * {@link RandomNumberGenerator}, {@link InstructionPointer} and {@link Stack}.
     * @param debug if executing command should print info. Will degrade performance if true.
     * @param unsafe if simulated ram should be protected from leaking into actual ram.
     */
    public Machine(boolean debug, boolean unsafe){
        register = new GeneralPurposeRegister();
        floatingPointRegister = new FloatingPointRegister();
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
     * <li>{@link GeneralPurposeRegister#REMAINDER} is set to 0
     * <li>{@link GeneralPurposeRegister#POINTER} is set to 0
     * <li>{@link GeneralPurposeRegister#PROGRAM_SECTION} is set to address of program section start
     * <li>{@link GeneralPurposeRegister#DATA_SECTION} is set to address of data section start
     * <li>{@link GeneralPurposeRegister#EXTRA_DATA_SECTION} is set to address of extra data section start
     * <li>{@link GeneralPurposeRegister#STACK_SECTION} is set to address of stack section start
     * <li>{@link GeneralPurposeRegister#STACK_POINTER} is set to address of stack section start
     * <li>{@link GeneralPurposeRegister#STACK_FRAME_POINTER} is set to address of stack section start
     * </ul><p>
     */
    private void setupRegisterAddresses(){
        Random random = new Random();
        int programSection = random.nextInt(4) * SECTION_SIZE;
        int dataSection = programSection + (1 + random.nextInt(3)) * SECTION_SIZE;
        int extendedDataSection = dataSection + (1 + random.nextInt(3)) * SECTION_SIZE;
        int stackSection = extendedDataSection + (1 + random.nextInt(3)) * SECTION_SIZE;
        register.set(REMAINDER, 0);
        register.set(POINTER, 0);
        register.set(PROGRAM_SECTION, programSection);
        register.set(DATA_SECTION, dataSection);
        register.set(EXTRA_DATA_SECTION, extendedDataSection);
        register.set(STACK_SECTION, stackSection);
        register.set(STACK_POINTER, stackSection);
        register.set(STACK_FRAME_POINTER, stackSection);
    }

    /**
     * Loads program into memory. Sections(program, data, extra data, stack) are allocated during the process
     * and General Purpose {@link GeneralPurposeRegister} {@link Machine#register}s are set up accordingly. For more info
     * see {@link Machine#setupRegisterAddresses()}.
     * @param program is loaded into memory starting from address stored at {@link GeneralPurposeRegister#PROGRAM_SECTION PROGRAM_SECTION}
     */
    public void loadProgram(Program program){
        setupRegisterAddresses();
        int programPointer = register.get(PROGRAM_SECTION);
        int dataPointer = register.get(DATA_SECTION);
        for (var declaration : program.getDeclarations())
            dataPointer=declaration.declare(ram, dataPointer);
        if(debug) System.out.println("Program:");
        for (var instruction : program.getInstructions())
            programPointer=instruction.loadIntoMemory(debug, ram, programPointer);
        if(debug) System.out.println();
        instructionPointer.set(register.get(PROGRAM_SECTION));
        register.set(POINTER, dataPointer);
    }

    /**
     * Runs program starting from instruction at address stored in {@link InstructionPointer}.
     * Ends when reaches {@link com.hermant.program.instruction.ExitInstruction ExitInstruction} or
     * when unrecognizable instruction is loaded resulting in {@link IllegalStateException}.
     * @param sleep time in milliseconds to sleep in between instructions
     */
    public void runProgram(int sleep){
        System.out.print("Program has started\n");
        running = true;
        if(debug) setDebugSignalHandling(); else setSignalHandling();
        long millis;
        if(debug) millis = debug(sleep);
        else if(sleep > 0) millis = run(sleep);
        else millis = run();
        System.out.printf("\nExecuted %s instructions in %dms.\n",
                Long.toUnsignedString(executedCounter), millis);
        executedCounter = 0;
    }

    /**
     * Runs program in debug mode, with optional sleep.
     * @param sleep time in milliseconds to sleep in between instructions
     * @return time this program was running
     */
    private long debug(int sleep){
        final long start = System.nanoTime();
        do {
            InstructionUtils.fetchInstruction(ram, instructionPointer.get()).debug(this);
            executedCounter++;
            checkForPaused();
            sleep(sleep);
        } while(running);
        return TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);
    }

    /**
     * Runs program in run mode, without sleep.
     * @return time this program was running
     */
    private long run(){
        final long start = System.nanoTime();
        do {
            InstructionUtils.fetchInstruction(ram, instructionPointer.get()).run(this);
            executedCounter++;
        } while(running);
        return TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);
    }

    /**
     * Runs program in run mode, with sleep.
     * @param sleep time in milliseconds to sleep in between instructions
     * @return time this program was running
     */
    private long run(int sleep){
        final long start = System.nanoTime();
        do {
            InstructionUtils.fetchInstruction(ram, instructionPointer.get()).run(this);
            executedCounter++;
            sleep(sleep);
        } while(running);
        return TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);
    }

    /**
     * Checks if program is paused(ie. {@link Machine#paused} is set to true). If it is paused it waits until paused
     * is again set to false.
     */
    private void checkForPaused() {
        synchronized (MONITOR){
            while(paused){
                try{
                    MONITOR.wait();
                } catch (InterruptedException ignored) {}
            }
        }
    }

    /**
     * Will set {@link Machine#paused} to true. If machine's is in debug mode the execution of the program will pause.
     */
    private void pauseThread() {
        paused = true;
    }

    /**
     * Will set {@link Machine#paused} to false. If machine's is in debug mode the execution of the program will resume.
     */
    private void resumeThread() {
        synchronized (MONITOR) {
            paused = false;
            MONITOR.notify();
        }
    }

    /**
     * Sets handling of SIGINT. The behaviour is as follows:
     * <p><ul>
     * <li>Execution of program is paused
     * <li>Handler waits for input.
     * <li>If it receives input, execution is resumed.
     * <li>If it receives another SIGINT the execution is stopped.
     * </ul><p>
     */
    private void setDebugSignalHandling(){
        Thread mainThread = Thread.currentThread();
        Signal.handle(new Signal("INT"), sig -> {
            pauseThread();
            Thread signalThread = Thread.currentThread();
            Signal.handle(new Signal("INT"), s -> {
                signalThread.interrupt();
                mainThread.interrupt();
                stop();
            });
            FileInputStream in = new FileInputStream(FileDescriptor.in);
            try {
                int i = -1;
                while(i != 10)
                    if (in.available() > 0) i = in.read();
                    else Thread.sleep(1);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            catch (InterruptedException ignored) { }
            setDebugSignalHandling();
            resumeThread();
        });
    }

    /**
     * Sets handling of SIGINT which behaviour is to stop execution of the program.
     */
    private void setSignalHandling(){
        Thread mainThread = Thread.currentThread();
        Signal.handle(new Signal("INT"), sig -> {
            mainThread.interrupt();
            stop();
        });
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
     * Stops execution of the program
     */
    public void stop(){
        running = false;
    }

    /**
     * @return Machine's General Purpose {@link GeneralPurposeRegister}:{@link Machine#register}
     */
    public GeneralPurposeRegister getRegister() {
        return register;
    }

    /**
     * @return Machine's Floating Point {@link GeneralPurposeRegister}:{@link Machine#floatingPointRegister}
     */
    public FloatingPointRegister getFPR() {
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

