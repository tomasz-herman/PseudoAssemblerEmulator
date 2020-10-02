package com.hermant.program.instruction;

import com.hermant.machine.Machine;
import com.hermant.machine.Stack;
import com.hermant.machine.register.GeneralPurposeRegister;
import com.hermant.machine.register.InstructionPointer;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class CallInstructionTest {

    @Test
    public void shouldPushNextInstructionPointerToStackWhenRan() {
        int initialPointer = 64;
        GeneralPurposeRegister gpr = getGprMock(0);
        Stack stack = getStackMock();
        InstructionPointer pointer = getInstructionPointerMock(initialPointer);
        Machine machine = getMachineMock(gpr, stack, pointer);
        Instruction instruction = new CallInstruction();

        instruction.run(machine);

        Mockito.verify(stack).push(initialPointer + instruction.instLength());
    }

    @Test
    public void shouldJumpToCalledLocationWhenRan() {
        int initialPointer = 64;
        int gprVal = 720;
        short offset = -12;
        GeneralPurposeRegister gpr = getGprMock(gprVal);
        Stack stack = getStackMock();
        InstructionPointer pointer = getInstructionPointerMock(initialPointer);
        Machine machine = getMachineMock(gpr, stack, pointer);
        Instruction instruction = new CallInstruction();

        instruction.ramOffset = offset;
        instruction.run(machine);

        Mockito.verify(pointer).set(gprVal + offset);
    }

    @NotNull
    private InstructionPointer getInstructionPointerMock(int initial) {
        InstructionPointer pointer = Mockito.mock(InstructionPointer.class);
        Mockito.when(pointer.get()).thenReturn(initial);
        return pointer;
    }

    @NotNull
    private Machine getMachineMock(GeneralPurposeRegister gpr, Stack stack, InstructionPointer pointer) {
        Machine machine = Mockito.mock(Machine.class);
        Mockito.when(machine.getRegister()).thenReturn(gpr);
        Mockito.when(machine.getStack()).thenReturn(stack);
        Mockito.when(machine.getInstructionPointer()).thenReturn(pointer);
        return machine;
    }

    @NotNull
    private GeneralPurposeRegister getGprMock(int onGet) {
        GeneralPurposeRegister gpr = Mockito.mock(GeneralPurposeRegister.class);
        Mockito.when(gpr.get(Mockito.anyInt())).thenReturn(onGet);
        return gpr;
    }

    @NotNull
    private Stack getStackMock() {
        return Mockito.mock(Stack.class);
    }

}