package com.hermant.program.instruction;

import com.hermant.machine.RandomAccessMemory;
import com.hermant.machine.Register;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.hermant.machine.Register.INSTRUCTION_POINTER;
import static com.hermant.program.instruction.Instruction.*;

public abstract class InstructionFactory {

    //command design pattern
    public interface ChineseWorker { Instruction create(Byte reg1, Byte reg2, Integer ramAddress); }

    private static final Map<Byte, ChineseWorker> INSTRUCTIONS;

    static {
        final Map<Byte, ChineseWorker> instructions = new HashMap<>();
        INSTRUCTIONS = Collections.unmodifiableMap(instructions);
        //116 chinese workers:
        instructions.put(RETURN, ReturnInstruction::new);
        instructions.put(LOAD, LoadInstruction::new);
        instructions.put(LOAD_REGISTER, LoadRegisterInstruction::new);
        instructions.put(STORE, StoreInstruction::new);
        instructions.put(LOAD_FLOAT, LoadFloatInstruction::new);
        instructions.put(LOAD_REGISTER_FLOAT, LoadRegisterFloatInstruction::new);
        instructions.put(STORE_FLOAT, StoreFloatInstruction::new);
        instructions.put(LOAD_ADDRESS, LoadAddressInstruction::new);
        instructions.put(EXCHANGE, ExchangeInstruction::new);
        instructions.put(EXCHANGE_REGISTER, ExchangeRegisterInstruction::new);
        instructions.put(EXCHANGE_FLOAT, ExchangeFloatInstruction::new);
        instructions.put(EXCHANGE_REGISTER_FLOAT, ExchangeRegisterFloatInstruction::new);
        instructions.put(STORE_BYTE, StoreByteInstruction::new);
        instructions.put(LOAD_BYTE_UNSIGNED, LoadByteUnsignedInstruction::new);
        instructions.put(LOAD_BYTE, LoadByteInstruction::new);
        instructions.put(LOAD_INTEGER_AS_FLOAT, LoadIntegerAsFloatInstruction::new);
        instructions.put(STORE_FLOAT_AS_INTEGER, StoreFloatAsIntegerInstruction::new);
        instructions.put(RANDOM, RandomInstruction::new);
        instructions.put(RANDOM_REGISTER, RandomRegisterInstruction::new);
        instructions.put(ENTER, EnterInstruction::new);
        instructions.put(LEAVE, LeaveInstruction::new);
        instructions.put(OUTPUT, OutputInstruction::new);
        instructions.put(OUTPUT_SIGNED, OutputSignedInstruction::new);
        instructions.put(OUTPUT_FLOAT, OutputFloatInstruction::new);
        instructions.put(OUTPUT_BYTE, OutputByteInstruction::new);
        instructions.put(OUTPUT_CHAR, OutputCharInstruction::new);
        instructions.put(OUTPUT_REGISTER, OutputRegisterInstruction::new);
        instructions.put(OUTPUT_REGISTER_SIGNED, OutputRegisterSignedInstruction::new);
        instructions.put(OUTPUT_REGISTER_FLOAT, OutputRegisterFloatInstruction::new);
        instructions.put(OUTPUT_REGISTER_BYTE, OutputRegisterByteInstruction::new);
        instructions.put(OUTPUT_REGISTER_CHAR, OutputRegisterCharInstruction::new);
        instructions.put(NO_OPERATION, NoOperationInstruction::new);
        instructions.put(PUSH, PushInstruction::new);
        instructions.put(PUSH_REGISTER, PushRegisterInstruction::new);
        instructions.put(PUSH_REGISTER_FLOAT, PushRegisterFloatInstruction::new);
        instructions.put(PUSH_FLAGS, PushFlagsInstruction::new);
        instructions.put(POP, PopInstruction::new);
        instructions.put(POP_REGISTER, PopRegisterInstruction::new);
        instructions.put(POP_REGISTER_FLOAT, PopRegisterFloatInstruction::new);
        instructions.put(POP_FLAGS, PopFlagsInstruction::new);
        instructions.put(ADD, AddInstruction::new);
        instructions.put(ADD_REGISTER, AddRegisterInstruction::new);
        instructions.put(SUBTRACT, SubtractInstruction::new);
        instructions.put(SUBTRACT_REGISTER, SubtractRegisterInstruction::new);
        instructions.put(MULTIPLY, MultiplyInstruction::new);
        instructions.put(MULTIPLY_REGISTER, MultiplyRegisterInstruction::new);
        instructions.put(DIVIDE, DivideInstruction::new);
        instructions.put(DIVIDE_REGISTER, DivideRegisterInstruction::new);
        instructions.put(DIVIDE_SIGNED, DivideSignedInstruction::new);
        instructions.put(DIVIDE_SIGNED_REGISTER, DivideSignedRegisterInstruction::new);
        instructions.put(COMPARE, CompareInstruction::new);
        instructions.put(COMPARE_REGISTER, CompareRegisterInstruction::new);
        instructions.put(COMPARE_FLOAT, CompareFloatInstruction::new);
        instructions.put(COMPARE_REGISTER_FLOAT, CompareRegisterFloatInstruction::new);
        instructions.put(ADD_FLOAT, AddFloatInstruction::new);
        instructions.put(ADD_REGISTER_FLOAT, AddRegisterFloatInstruction::new);
        instructions.put(SUBTRACT_FLOAT, SubtractFloatInstruction::new);
        instructions.put(SUBTRACT_REGISTER_FLOAT, SubtractRegisterFloatInstruction::new);
        instructions.put(MULTIPLY_FLOAT, MultiplyFloatInstruction::new);
        instructions.put(MULTIPLY_REGISTER_FLOAT, MultiplyRegisterFloatInstruction::new);
        instructions.put(DIVIDE_FLOAT, DivideFloatInstruction::new);
        instructions.put(DIVIDE_REGISTER_FLOAT, DivideRegisterFloatInstruction::new);
        instructions.put(SQUARE_ROOT_FLOAT, SquareRootFloatInstruction::new);
        instructions.put(ABSOLUTE_FLOAT, AbsoluteFloatInstruction::new);
        instructions.put(SINE_FLOAT, SineFloatInstruction::new);
        instructions.put(COSINE_FLOAT, CosineFloatInstruction::new);
        instructions.put(TANGENT_FLOAT, TangentFloatInstruction::new);
        instructions.put(EXAMINE_FLOAT, ExamineFloatInstruction::new);
        instructions.put(EXAMINE_FLOAT_REGISTER, ExamineFloatRegisterInstruction::new);
        instructions.put(TEST_FLOAT, TestFloatInstruction::new);
        instructions.put(TEST_FLOAT_REGISTER, TestFloatRegisterInstruction::new);
        instructions.put(NEGATE, NegateInstruction::new);
        instructions.put(NEGATE_REGISTER, NegateRegisterInstruction::new);
        instructions.put(INCREMENT, IncrementInstruction::new);
        instructions.put(INCREMENT_REGISTER, IncrementRegisterInstruction::new);
        instructions.put(DECREMENT, DecrementInstruction::new);
        instructions.put(DECREMENT_REGISTER, DecrementRegisterInstruction::new);
        instructions.put(TEST, TestInstruction::new);
        instructions.put(TEST_REGISTER, TestRegisterInstruction::new);
        instructions.put(AND, AndInstruction::new);
        instructions.put(AND_REGISTER, AndRegisterInstruction::new);
        instructions.put(OR, OrInstruction::new);
        instructions.put(OR_REGISTER, OrRegisterInstruction::new);
        instructions.put(XOR, XorInstruction::new);
        instructions.put(XOR_REGISTER, XorRegisterInstruction::new);
        instructions.put(RIGHT_SHIFT_LOGICAL, RightShiftLogicalInstruction::new);
        instructions.put(LEFT_SHIFT_LOGICAL, LeftShiftLogicalInstruction::new);
        instructions.put(RIGHT_SHIFT_LOGICAL_REGISTER, RightShiftLogicalRegisterInstruction::new);
        instructions.put(LEFT_SHIFT_LOGICAL_REGISTER, LeftShiftLogicalRegisterInstruction::new);
        instructions.put(RIGHT_SHIFT_ARITHMETIC, RightShiftArithmeticInstruction::new);
        instructions.put(RIGHT_SHIFT_ARITHMETIC_REGISTER, RightShiftArithmeticRegisterInstruction::new);
        instructions.put(RIGHT_ROTATE, RightRotateInstruction::new);
        instructions.put(LEFT_ROTATE, LeftRotateInstruction::new);
        instructions.put(RIGHT_ROTATE_REGISTER, RightRotateRegisterInstruction::new);
        instructions.put(LEFT_ROTATE_REGISTER, LeftRotateRegisterInstruction::new);
        instructions.put(NOT_REGISTER, NotRegisterInstruction::new);
        instructions.put(NOT, NotInstruction::new);
        instructions.put(JUMP, JumpInstruction::new);
        instructions.put(JUMP_EQUAL, JumpEqualInstruction::new);
        instructions.put(JUMP_NOT_EQUAL, JumpNotEqualInstruction::new);
        instructions.put(JUMP_GREATER, JumpGreaterInstruction::new);
        instructions.put(JUMP_GREATER_OR_EQUAL, JumpGreaterOrEqualInstruction::new);
        instructions.put(JUMP_LESSER, JumpLesserInstruction::new);
        instructions.put(JUMP_LESS_OR_EQUAL, JumpLessOrEqualInstruction::new);
        instructions.put(JUMP_ABOVE, JumpAboveInstruction::new);
        instructions.put(JUMP_ABOVE_OR_EQUAL, JumpAboveOrEqualInstruction::new);
        instructions.put(JUMP_BELOW, JumpBelowInstruction::new);
        instructions.put(JUMP_BELOW_OR_EQUAL, JumpBelowOrEqualInstruction::new);
        instructions.put(JUMP_OVERFLOW, JumpOverflowInstruction::new);
        instructions.put(JUMP_NOT_OVERFLOW, JumpNotOverflowInstruction::new);
        instructions.put(JUMP_SIGNED, JumpSignedInstruction::new);
        instructions.put(JUMP_NOT_SIGNED, JumpNotSignedInstruction::new);
        instructions.put(JUMP_PARITY, JumpParityInstruction::new);
        instructions.put(JUMP_NOT_PARITY, JumpNotParityInstruction::new);
        instructions.put(LOOP, LoopInstruction::new);
        instructions.put(CALL, CallInstruction::new);
    }

    public static Instruction fetchNextInstruction(RandomAccessMemory ram, Register reg){
        int address = reg.getInteger(INSTRUCTION_POINTER);
        byte code = (byte)ram.getByte(address);
        byte reg1 = (byte)((ram.getByte(address+1)&0xf0)>>4);
        byte reg2 = (byte)(ram.getByte(address+1)&0xf);
        Integer ramOffset = null;
        if ((code & 0x10000000)!=0)
            switch(ram.endianness){
                case LittleEndian, MiddleEndian ->
                        ramOffset = (ram.getByte(address+3)<<8) + ram.getByte(address + 2);
                case BigEndian ->
                        ramOffset = (ram.getByte(address+2)<<8) + ram.getByte(address + 3);
            }
        ChineseWorker worker = INSTRUCTIONS.get(code);
        if(worker == null) throw new IllegalStateException("Unrecognizable instruction code: " + String.format("%1$02X",code));
        return worker.create(reg1, reg2, ramOffset);
    }
}
