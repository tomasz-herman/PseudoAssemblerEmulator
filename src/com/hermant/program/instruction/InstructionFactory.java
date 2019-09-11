package com.hermant.program.instruction;

import com.hermant.machine.register.InstructionPointer;
import com.hermant.machine.ram.RandomAccessMemory;

import static com.hermant.program.instruction.Instruction.*;

public final class InstructionFactory {

    private InstructionFactory() {}

    public interface Supplier{ Instruction get(); }

    private static int BYTE_TO_UNSIGNED = 0xff;

    private static final Supplier[] INSTRUCTION_CONSTRUCTORS;
    private static final Instruction[] INSTRUCTIONS;
    static final int[] INSTRUCTION_LENGTHS;
    static final String[] INSTRUCTION_CODES;

    static {
        int BYTES_QUANTITY = 256;
        INSTRUCTION_LENGTHS  = new int[BYTES_QUANTITY];
        INSTRUCTION_CODES  = new String[BYTES_QUANTITY];
        INSTRUCTIONS = new Instruction[BYTES_QUANTITY];
        INSTRUCTION_CONSTRUCTORS = new Supplier[BYTES_QUANTITY];
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & EXIT] = ExitInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & RETURN] = ReturnInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & LOAD] = LoadInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & LOAD_REGISTER] = LoadRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & STORE] = StoreInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & LOAD_FLOAT] = LoadFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & LOAD_REGISTER_FLOAT] = LoadRegisterFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & STORE_FLOAT] = StoreFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & LOAD_ADDRESS] = LoadAddressInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & EXCHANGE] = ExchangeInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & EXCHANGE_REGISTER] = ExchangeRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & EXCHANGE_FLOAT] = ExchangeFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & EXCHANGE_REGISTER_FLOAT] = ExchangeRegisterFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & STORE_BYTE] = StoreByteInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & LOAD_BYTE_UNSIGNED] = LoadByteUnsignedInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & LOAD_BYTE] = LoadByteInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & LOAD_INTEGER_AS_FLOAT] = LoadIntegerAsFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & STORE_FLOAT_AS_INTEGER] = StoreFloatAsIntegerInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & RANDOM] = RandomInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & RANDOM_REGISTER] = RandomRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & ENTER] = EnterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & LEAVE] = LeaveInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & OUTPUT] = OutputInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & OUTPUT_SIGNED] = OutputSignedInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & OUTPUT_FLOAT] = OutputFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & OUTPUT_BYTE] = OutputByteInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & OUTPUT_CHAR] = OutputCharInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & OUTPUT_REGISTER] = OutputRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & OUTPUT_REGISTER_SIGNED] = OutputRegisterSignedInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & OUTPUT_REGISTER_FLOAT] = OutputRegisterFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & OUTPUT_REGISTER_BYTE] = OutputRegisterByteInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & OUTPUT_REGISTER_CHAR] = OutputRegisterCharInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & NO_OPERATION] = NoOperationInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & PUSH] = PushInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & PUSH_REGISTER] = PushRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & PUSH_REGISTER_FLOAT] = PushRegisterFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & PUSH_FLAGS] = PushFlagsInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & PUSH_ALL] = PushAllInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & PUSH_ALL_FLOAT] = PushAllFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & POP] = PopInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & POP_REGISTER] = PopRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & POP_REGISTER_FLOAT] = PopRegisterFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & POP_FLAGS] = PopFlagsInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & POP_ALL] = PopAllInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & POP_ALL_FLOAT] = PopAllFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & ADD] = AddInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & ADD_REGISTER] = AddRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & SUBTRACT] = SubtractInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & SUBTRACT_REGISTER] = SubtractRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & MULTIPLY] = MultiplyInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & MULTIPLY_REGISTER] = MultiplyRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & DIVIDE] = DivideInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & DIVIDE_REGISTER] = DivideRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & DIVIDE_SIGNED] = DivideSignedInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & DIVIDE_SIGNED_REGISTER] = DivideSignedRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & COMPARE] = CompareInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & COMPARE_REGISTER] = CompareRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & COMPARE_FLOAT] = CompareFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & COMPARE_REGISTER_FLOAT] = CompareRegisterFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & ADD_FLOAT] = AddFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & ADD_REGISTER_FLOAT] = AddRegisterFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & SUBTRACT_FLOAT] = SubtractFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & SUBTRACT_REGISTER_FLOAT] = SubtractRegisterFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & MULTIPLY_FLOAT] = MultiplyFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & MULTIPLY_REGISTER_FLOAT] = MultiplyRegisterFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & DIVIDE_FLOAT] = DivideFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & DIVIDE_REGISTER_FLOAT] = DivideRegisterFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & SQUARE_ROOT_FLOAT] = SquareRootFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & ABSOLUTE_FLOAT] = AbsoluteFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & SINE_FLOAT] = SineFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & COSINE_FLOAT] = CosineFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & TANGENT_FLOAT] = TangentFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & EXAMINE_FLOAT] = ExamineFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & EXAMINE_FLOAT_REGISTER] = ExamineFloatRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & TEST_FLOAT] = TestFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & TEST_FLOAT_REGISTER] = TestFloatRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & NEGATE] = NegateInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & NEGATE_REGISTER] = NegateRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & INCREMENT] = IncrementInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & INCREMENT_REGISTER] = IncrementRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & DECREMENT] = DecrementInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & DECREMENT_REGISTER] = DecrementRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & TEST] = TestInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & TEST_REGISTER] = TestRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & AND] = AndInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & AND_REGISTER] = AndRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & OR] = OrInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & OR_REGISTER] = OrRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & XOR] = XorInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & XOR_REGISTER] = XorRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & RIGHT_SHIFT_LOGICAL] = RightShiftLogicalInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & LEFT_SHIFT_LOGICAL] = LeftShiftLogicalInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & RIGHT_SHIFT_LOGICAL_REGISTER] = RightShiftLogicalRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & LEFT_SHIFT_LOGICAL_REGISTER] = LeftShiftLogicalRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & RIGHT_SHIFT_ARITHMETIC] = RightShiftArithmeticInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & RIGHT_SHIFT_ARITHMETIC_REGISTER] = RightShiftArithmeticRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & RIGHT_ROTATE] = RightRotateInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & LEFT_ROTATE] = LeftRotateInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & RIGHT_ROTATE_REGISTER] = RightRotateRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & LEFT_ROTATE_REGISTER] = LeftRotateRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & NOT_REGISTER] = NotRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & NOT] = NotInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & JUMP] = JumpInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & JUMP_EQUAL] = JumpEqualInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & JUMP_NOT_EQUAL] = JumpNotEqualInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & JUMP_GREATER] = JumpGreaterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & JUMP_GREATER_OR_EQUAL] = JumpGreaterOrEqualInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & JUMP_LESSER] = JumpLesserInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & JUMP_LESS_OR_EQUAL] = JumpLessOrEqualInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & JUMP_ABOVE] = JumpAboveInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & JUMP_ABOVE_OR_EQUAL] = JumpAboveOrEqualInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & JUMP_BELOW] = JumpBelowInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & JUMP_BELOW_OR_EQUAL] = JumpBelowOrEqualInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & JUMP_OVERFLOW] = JumpOverflowInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & JUMP_NOT_OVERFLOW] = JumpNotOverflowInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & JUMP_SIGNED] = JumpSignedInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & JUMP_NOT_SIGNED] = JumpNotSignedInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & JUMP_PARITY] = JumpParityInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & JUMP_NOT_PARITY] = JumpNotParityInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & LOOP] = LoopInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & CALL] = CallInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & INPUT] = InputInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & INPUT_REGISTER] = InputRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & HALT] = HaltInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & TIME] = TimeInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & TIME_REGISTER] = TimeRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & SLEEP] = SleepInstruction::new;
        INSTRUCTION_CONSTRUCTORS[BYTE_TO_UNSIGNED & SLEEP_REGISTER] = SleepRegisterInstruction::new;
        for (int i = 0; i < BYTES_QUANTITY; i++) {
            if(INSTRUCTION_CONSTRUCTORS[i]==null) continue;
            final Instruction instruction = INSTRUCTION_CONSTRUCTORS[i].get();
            INSTRUCTIONS[i] = instruction;
            INSTRUCTION_LENGTHS[i] = instruction.instLength();
            INSTRUCTION_CODES[i] = instruction.instCode();
        }
    }

    public static Instruction fetchInstruction(RandomAccessMemory ram, int address){
        final int code = BYTE_TO_UNSIGNED & ram.getByte(address++);
        final Instruction instruction = INSTRUCTIONS[code];
        if(instruction == null)
            throw new IllegalStateException("Unrecognizable instruction code: " + String.format("%1$02X",(byte)code));
        final byte reg = ram.getByte(address++);
        instruction.reg1 = (byte)(reg>>4&0xf);
        instruction.reg2 = (byte)(reg&0xf);
        if(INSTRUCTION_LENGTHS[code] == 4)
            instruction.ramOffset = ram.getShort(address);
        return instruction;
    }

    public static Instruction fetchNewInstruction(RandomAccessMemory ram, int address){
        final int code = BYTE_TO_UNSIGNED & ram.getByte(address++);
        final Supplier constructor = INSTRUCTION_CONSTRUCTORS[code];
        if(constructor == null)
            throw new IllegalStateException("Unrecognizable instruction code: " + String.format("%1$02X",(byte)code));
        final Instruction instruction = constructor.get();
        final byte reg = ram.getByte(address++);
        instruction.reg1 = (byte)(reg>>4&0xf);
        instruction.reg2 = (byte)(reg&0xf);
        if(INSTRUCTION_LENGTHS[code] == 4)
            instruction.ramOffset = ram.getShort(address);
        return instruction;
    }
}
