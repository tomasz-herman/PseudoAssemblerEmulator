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
    public interface ChineseWorker { Instruction create(); }

    private static final Map<Byte, ChineseWorker> INSTRUCTIONS;

    static {
        final Map<Byte, ChineseWorker> instructions = new HashMap<>();
        INSTRUCTIONS = Collections.unmodifiableMap(instructions);
        //116 chinese workers:
        instructions.put(RETURN, () -> new ReturnInstruction(null, null, null));
        instructions.put(LOAD, () -> new LoadInstruction(null, null, null));
        instructions.put(LOAD_REGISTER, () -> new LoadRegisterInstruction(null, null, null));
        instructions.put(STORE, () -> new StoreInstruction(null, null, null));
        instructions.put(LOAD_FLOAT, () -> new LoadFloatInstruction(null, null, null));
        instructions.put(LOAD_REGISTER_FLOAT, () -> new LoadRegisterFloatInstruction(null, null, null));
        instructions.put(STORE_FLOAT, () -> new StoreFloatInstruction(null, null, null));
        instructions.put(LOAD_ADDRESS, () -> new LoadAddressInstruction(null, null, null));
        instructions.put(EXCHANGE, () -> new ExchangeInstruction(null, null, null));
        instructions.put(EXCHANGE_REGISTER, () -> new ExchangeRegisterInstruction(null, null, null));
        instructions.put(EXCHANGE_FLOAT, () -> new ExchangeFloatInstruction(null, null, null));
        instructions.put(EXCHANGE_REGISTER_FLOAT, () ->  new ExchangeRegisterFloatInstruction(null, null, null));
        instructions.put(STORE_BYTE, () -> new StoreByteInstruction(null, null, null));
        instructions.put(LOAD_BYTE_UNSIGNED, () -> new LoadByteUnsignedInstruction(null, null, null));
        instructions.put(LOAD_BYTE, () -> new LoadByteInstruction(null, null, null));
        instructions.put(LOAD_INTEGER_AS_FLOAT, () -> new LoadIntegerAsFloatInstruction(null, null, null));
        instructions.put(STORE_FLOAT_AS_INTEGER, () -> new StoreFloatAsIntegerInstruction(null, null, null));
        instructions.put(RANDOM, () -> new RandomInstruction(null, null, null));
        instructions.put(RANDOM_REGISTER, () -> new RandomRegisterInstruction(null, null, null));
        instructions.put(ENTER, () -> new EnterInstruction(null, null, null));
        instructions.put(LEAVE, () -> new LeaveInstruction(null, null, null));
        instructions.put(OUTPUT, () -> new OutputInstruction(null, null, null));
        instructions.put(OUTPUT_SIGNED, () -> new OutputSignedInstruction(null, null, null));
        instructions.put(OUTPUT_FLOAT, () -> new OutputFloatInstruction(null, null, null));
        instructions.put(OUTPUT_BYTE, () -> new OutputByteInstruction(null, null, null));
        instructions.put(OUTPUT_CHAR, () -> new OutputCharInstruction(null, null, null));
        instructions.put(OUTPUT_REGISTER, () -> new OutputRegisterInstruction(null, null, null));
        instructions.put(OUTPUT_REGISTER_SIGNED, () -> new OutputRegisterSignedInstruction(null, null, null));
        instructions.put(OUTPUT_REGISTER_FLOAT, () -> new OutputRegisterFloatInstruction(null, null, null));
        instructions.put(OUTPUT_REGISTER_BYTE, () -> new OutputRegisterByteInstruction(null, null, null));
        instructions.put(OUTPUT_REGISTER_CHAR, () -> new OutputRegisterCharInstruction(null, null, null));
        instructions.put(NO_OPERATION, () -> new NoOperationInstruction(null, null, null));
        instructions.put(PUSH, () -> new PushInstruction(null, null, null));
        instructions.put(PUSH_REGISTER, () -> new PushRegisterInstruction(null, null, null));
        instructions.put(PUSH_REGISTER_FLOAT, () -> new PushRegisterFloatInstruction(null, null, null));
        instructions.put(PUSH_FLAGS, () -> new PushFlagsInstruction(null, null, null));
        instructions.put(POP, () -> new PopInstruction(null, null, null));
        instructions.put(POP_REGISTER, () -> new PopRegisterInstruction(null, null, null));
        instructions.put(POP_REGISTER_FLOAT, () -> new PopRegisterFloatInstruction(null, null, null));
        instructions.put(POP_FLAGS, () -> new PopFlagsInstruction(null, null, null));
        instructions.put(ADD, () -> new AddInstruction(null, null, null));
        instructions.put(ADD_REGISTER, () -> new AddRegisterInstruction(null, null, null));
        instructions.put(SUBTRACT, () -> new SubtractInstruction(null, null, null));
        instructions.put(SUBTRACT_REGISTER, () -> new SubtractRegisterInstruction(null, null, null));
        instructions.put(MULTIPLY, () -> new MultiplyInstruction(null, null, null));
        instructions.put(MULTIPLY_REGISTER, () -> new MultiplyRegisterInstruction(null, null, null));
        instructions.put(DIVIDE, () -> new DivideInstruction(null, null, null));
        instructions.put(DIVIDE_REGISTER, () -> new DivideRegisterInstruction(null, null, null));
        instructions.put(DIVIDE_SIGNED, () -> new DivideSignedInstruction(null, null, null));
        instructions.put(DIVIDE_SIGNED_REGISTER, () -> new DivideSignedRegisterInstruction(null, null, null));
        instructions.put(COMPARE, () -> new CompareInstruction(null, null, null));
        instructions.put(COMPARE_REGISTER, () -> new CompareRegisterInstruction(null, null, null));
        instructions.put(COMPARE_FLOAT, () -> new CompareFloatInstruction(null, null, null));
        instructions.put(COMPARE_REGISTER_FLOAT, () -> new CompareRegisterFloatInstruction(null, null, null));
        instructions.put(ADD_FLOAT, () -> new AddFloatInstruction(null, null, null));
        instructions.put(ADD_REGISTER_FLOAT, () -> new AddRegisterFloatInstruction(null, null, null));
        instructions.put(SUBTRACT_FLOAT, () -> new SubtractFloatInstruction(null, null, null));
        instructions.put(SUBTRACT_REGISTER_FLOAT, () -> new SubtractRegisterFloatInstruction(null, null, null));
        instructions.put(MULTIPLY_FLOAT, () -> new MultiplyFloatInstruction(null, null, null));
        instructions.put(MULTIPLY_REGISTER_FLOAT, () -> new MultiplyRegisterFloatInstruction(null, null, null));
        instructions.put(DIVIDE_FLOAT, () -> new DivideFloatInstruction(null, null, null));
        instructions.put(DIVIDE_REGISTER_FLOAT, () -> new DivideRegisterFloatInstruction(null, null, null));
        instructions.put(SQUARE_ROOT_FLOAT, () -> new SquareRootFloatInstruction(null, null, null));
        instructions.put(ABSOLUTE_FLOAT, () -> new AbsoluteFloatInstruction(null, null, null));
        instructions.put(SINE_FLOAT, () -> new SineFloatInstruction(null, null, null));
        instructions.put(COSINE_FLOAT, () -> new CosineFloatInstruction(null, null, null));
        instructions.put(TANGENT_FLOAT, () -> new TangentFloatInstruction(null, null, null));
        instructions.put(EXAMINE_FLOAT, () -> new ExamineFloatInstruction(null, null, null));
        instructions.put(EXAMINE_FLOAT_REGISTER, () -> new ExamineFloatRegisterInstruction(null, null, null));
        instructions.put(TEST_FLOAT, () -> new TestFloatInstruction(null, null, null));
        instructions.put(TEST_FLOAT_REGISTER, () -> new TestFloatRegisterInstruction(null, null, null));
        instructions.put(NEGATE, () -> new NegateInstruction(null, null, null));
        instructions.put(NEGATE_REGISTER, () -> new NegateRegisterInstruction(null, null, null));
        instructions.put(INCREMENT, () -> new IncrementInstruction(null, null, null));
        instructions.put(INCREMENT_REGISTER, () -> new IncrementRegisterInstruction(null, null, null));
        instructions.put(DECREMENT, () -> new DecrementInstruction(null, null, null));
        instructions.put(DECREMENT_REGISTER, () -> new DecrementRegisterInstruction(null, null, null));
        instructions.put(TEST, () -> new TestInstruction(null, null, null));
        instructions.put(TEST_REGISTER, () -> new TestRegisterInstruction(null, null, null));
        instructions.put(AND, () -> new AndInstruction(null, null, null));
        instructions.put(AND_REGISTER, () -> new AndRegisterInstruction(null, null, null));
        instructions.put(OR, () -> new OrInstruction(null, null, null));
        instructions.put(OR_REGISTER, () -> new OrRegisterInstruction(null, null, null));
        instructions.put(XOR, () -> new XorInstruction(null, null, null));
        instructions.put(XOR_REGISTER, () -> new XorRegisterInstruction(null, null, null));
        instructions.put(RIGHT_SHIFT_LOGICAL, () -> new RightShiftLogicalInstruction(null, null, null));
        instructions.put(LEFT_SHIFT_LOGICAL, () -> new LeftShiftLogicalInstruction(null, null, null));
        instructions.put(RIGHT_SHIFT_LOGICAL_REGISTER, () -> new RightShiftLogicalRegisterInstruction(null, null, null));
        instructions.put(LEFT_SHIFT_LOGICAL_REGISTER, () -> new LeftShiftLogicalRegisterInstruction(null, null, null));
        instructions.put(RIGHT_SHIFT_ARITHMETIC, () -> new RightShiftArithmeticInstruction(null, null, null));
        instructions.put(RIGHT_SHIFT_ARITHMETIC_REGISTER, () -> new RightShiftArithmeticRegisterInstruction(null, null, null));
        instructions.put(RIGHT_ROTATE, () -> new RightRotateInstruction(null, null, null));
        instructions.put(LEFT_ROTATE, () -> new LeftRotateInstruction(null, null, null));
        instructions.put(RIGHT_ROTATE_REGISTER, () -> new RightRotateRegisterInstruction(null, null, null));
        instructions.put(LEFT_ROTATE_REGISTER, () -> new LeftRotateRegisterInstruction(null, null, null));
        instructions.put(NOT_REGISTER, () -> new NotRegisterInstruction(null, null, null));
        instructions.put(NOT, () -> new NotInstruction(null, null, null));
        instructions.put(JUMP, () -> new JumpInstruction(null, null, null));
        instructions.put(JUMP_EQUAL, () -> new JumpEqualInstruction(null, null, null));
        instructions.put(JUMP_NOT_EQUAL, () -> new JumpNotEqualInstruction(null, null, null));
        instructions.put(JUMP_GREATER, () -> new JumpGreaterInstruction(null, null, null));
        instructions.put(JUMP_GREATER_OR_EQUAL, () -> new JumpGreaterOrEqualInstruction(null, null, null));
        instructions.put(JUMP_LESSER, () -> new JumpLesserInstruction(null, null, null));
        instructions.put(JUMP_LESS_OR_EQUAL, () -> new JumpLessOrEqualInstruction(null, null, null));
        instructions.put(JUMP_ABOVE, () -> new JumpAboveInstruction(null, null, null));
        instructions.put(JUMP_ABOVE_OR_EQUAL, () -> new JumpAboveOrEqualInstruction(null, null, null));
        instructions.put(JUMP_BELOW, () -> new JumpBelowInstruction(null, null, null));
        instructions.put(JUMP_BELOW_OR_EQUAL, () -> new JumpBelowOrEqualInstruction(null, null, null));
        instructions.put(JUMP_OVERFLOW, () -> new JumpOverflowInstruction(null, null, null));
        instructions.put(JUMP_NOT_OVERFLOW, () -> new JumpNotOverflowInstruction(null, null, null));
        instructions.put(JUMP_SIGNED, () -> new JumpSignedInstruction(null, null, null));
        instructions.put(JUMP_NOT_SIGNED, () -> new JumpNotSignedInstruction(null, null, null));
        instructions.put(JUMP_PARITY, () -> new JumpParityInstruction(null, null, null));
        instructions.put(JUMP_NOT_PARITY, () -> new JumpNotParityInstruction(null, null, null));
        instructions.put(LOOP, () -> new LoopInstruction(null, null, null));
        instructions.put(CALL, () -> new CallInstruction(null, null, null));
    }

    public static Instruction fetchNextInstruction(RandomAccessMemory ram, Register reg){
        int address = reg.getInteger(INSTRUCTION_POINTER);
        byte code = (byte)ram.getByte(address);
        ChineseWorker worker = INSTRUCTIONS.get(code);
        Instruction result = worker.create();
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
        result.reg1 = reg1;
        result.reg2 = reg2;
        result.ramOffset = ramOffset;
        return result;
    }
}
