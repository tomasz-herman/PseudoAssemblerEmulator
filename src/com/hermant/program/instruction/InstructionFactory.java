package com.hermant.program.instruction;

import com.hermant.machine.register.InstructionPointer;
import com.hermant.machine.ram.RandomAccessMemory;

import static com.hermant.program.instruction.Instruction.*;

public abstract class InstructionFactory {

    //command design pattern
    public interface InstructionConstructor { Instruction create(Byte reg1, Byte reg2, Short ramOffset); }

    private static final InstructionConstructor[] INSTRUCTION_CONSTRUCTORS;
    static final int[] INSTRUCTION_LENGTHS;
    static final String[] INSTRUCTION_CODES;

    static {
        INSTRUCTION_LENGTHS  = new int[256];
        INSTRUCTION_CODES  = new String[256];
        INSTRUCTION_CONSTRUCTORS = new InstructionConstructor[256];
        INSTRUCTION_CONSTRUCTORS[128 + EXIT] = ExitInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + RETURN] = ReturnInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + LOAD] = LoadInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + LOAD_REGISTER] = LoadRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + STORE] = StoreInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + LOAD_FLOAT] = LoadFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + LOAD_REGISTER_FLOAT] = LoadRegisterFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + STORE_FLOAT] = StoreFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + LOAD_ADDRESS] = LoadAddressInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + EXCHANGE] = ExchangeInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + EXCHANGE_REGISTER] = ExchangeRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + EXCHANGE_FLOAT] = ExchangeFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + EXCHANGE_REGISTER_FLOAT] = ExchangeRegisterFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + STORE_BYTE] = StoreByteInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + LOAD_BYTE_UNSIGNED] = LoadByteUnsignedInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + LOAD_BYTE] = LoadByteInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + LOAD_INTEGER_AS_FLOAT] = LoadIntegerAsFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + STORE_FLOAT_AS_INTEGER] = StoreFloatAsIntegerInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + RANDOM] = RandomInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + RANDOM_REGISTER] = RandomRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + ENTER] = EnterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + LEAVE] = LeaveInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + OUTPUT] = OutputInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + OUTPUT_SIGNED] = OutputSignedInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + OUTPUT_FLOAT] = OutputFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + OUTPUT_BYTE] = OutputByteInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + OUTPUT_CHAR] = OutputCharInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + OUTPUT_REGISTER] = OutputRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + OUTPUT_REGISTER_SIGNED] = OutputRegisterSignedInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + OUTPUT_REGISTER_FLOAT] = OutputRegisterFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + OUTPUT_REGISTER_BYTE] = OutputRegisterByteInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + OUTPUT_REGISTER_CHAR] = OutputRegisterCharInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + NO_OPERATION] = NoOperationInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + PUSH] = PushInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + PUSH_REGISTER] = PushRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + PUSH_REGISTER_FLOAT] = PushRegisterFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + PUSH_FLAGS] = PushFlagsInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + POP] = PopInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + POP_REGISTER] = PopRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + POP_REGISTER_FLOAT] = PopRegisterFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + POP_FLAGS] = PopFlagsInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + ADD] = AddInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + ADD_REGISTER] = AddRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + SUBTRACT] = SubtractInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + SUBTRACT_REGISTER] = SubtractRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + MULTIPLY] = MultiplyInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + MULTIPLY_REGISTER] = MultiplyRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + DIVIDE] = DivideInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + DIVIDE_REGISTER] = DivideRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + DIVIDE_SIGNED] = DivideSignedInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + DIVIDE_SIGNED_REGISTER] = DivideSignedRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + COMPARE] = CompareInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + COMPARE_REGISTER] = CompareRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + COMPARE_FLOAT] = CompareFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + COMPARE_REGISTER_FLOAT] = CompareRegisterFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + ADD_FLOAT] = AddFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + ADD_REGISTER_FLOAT] = AddRegisterFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + SUBTRACT_FLOAT] = SubtractFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + SUBTRACT_REGISTER_FLOAT] = SubtractRegisterFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + MULTIPLY_FLOAT] = MultiplyFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + MULTIPLY_REGISTER_FLOAT] = MultiplyRegisterFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + DIVIDE_FLOAT] = DivideFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + DIVIDE_REGISTER_FLOAT] = DivideRegisterFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + SQUARE_ROOT_FLOAT] = SquareRootFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + ABSOLUTE_FLOAT] = AbsoluteFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + SINE_FLOAT] = SineFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + COSINE_FLOAT] = CosineFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + TANGENT_FLOAT] = TangentFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + EXAMINE_FLOAT] = ExamineFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + EXAMINE_FLOAT_REGISTER] = ExamineFloatRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + TEST_FLOAT] = TestFloatInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + TEST_FLOAT_REGISTER] = TestFloatRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + NEGATE] = NegateInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + NEGATE_REGISTER] = NegateRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + INCREMENT] = IncrementInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + INCREMENT_REGISTER] = IncrementRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + DECREMENT] = DecrementInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + DECREMENT_REGISTER] = DecrementRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + TEST] = TestInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + TEST_REGISTER] = TestRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + AND] = AndInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + AND_REGISTER] = AndRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + OR] = OrInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + OR_REGISTER] = OrRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + XOR] = XorInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + XOR_REGISTER] = XorRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + RIGHT_SHIFT_LOGICAL] = RightShiftLogicalInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + LEFT_SHIFT_LOGICAL] = LeftShiftLogicalInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + RIGHT_SHIFT_LOGICAL_REGISTER] = RightShiftLogicalRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + LEFT_SHIFT_LOGICAL_REGISTER] = LeftShiftLogicalRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + RIGHT_SHIFT_ARITHMETIC] = RightShiftArithmeticInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + RIGHT_SHIFT_ARITHMETIC_REGISTER] = RightShiftArithmeticRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + RIGHT_ROTATE] = RightRotateInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + LEFT_ROTATE] = LeftRotateInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + RIGHT_ROTATE_REGISTER] = RightRotateRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + LEFT_ROTATE_REGISTER] = LeftRotateRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + NOT_REGISTER] = NotRegisterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + NOT] = NotInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + JUMP] = JumpInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + JUMP_EQUAL] = JumpEqualInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + JUMP_NOT_EQUAL] = JumpNotEqualInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + JUMP_GREATER] = JumpGreaterInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + JUMP_GREATER_OR_EQUAL] = JumpGreaterOrEqualInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + JUMP_LESSER] = JumpLesserInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + JUMP_LESS_OR_EQUAL] = JumpLessOrEqualInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + JUMP_ABOVE] = JumpAboveInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + JUMP_ABOVE_OR_EQUAL] = JumpAboveOrEqualInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + JUMP_BELOW] = JumpBelowInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + JUMP_BELOW_OR_EQUAL] = JumpBelowOrEqualInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + JUMP_OVERFLOW] = JumpOverflowInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + JUMP_NOT_OVERFLOW] = JumpNotOverflowInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + JUMP_SIGNED] = JumpSignedInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + JUMP_NOT_SIGNED] = JumpNotSignedInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + JUMP_PARITY] = JumpParityInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + JUMP_NOT_PARITY] = JumpNotParityInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + LOOP] = LoopInstruction::new;
        INSTRUCTION_CONSTRUCTORS[128 + CALL] = CallInstruction::new;
        for (int i = 0; i < 256; i++) {
            if(INSTRUCTION_CONSTRUCTORS[i]==null) continue;
            Instruction inst = INSTRUCTION_CONSTRUCTORS[i].create(null, null, null);
            INSTRUCTION_LENGTHS[i] = inst.instLength();
            INSTRUCTION_CODES[i] = inst.instCode();
        }
    }

    public static Instruction fetchNextInstruction(RandomAccessMemory ram, InstructionPointer instructionPointer){
        int address = instructionPointer.get();
        byte code = ram.getByte(address);
        InstructionConstructor constructor = INSTRUCTION_CONSTRUCTORS[128 + code];
        if(constructor == null) throw new IllegalStateException("Unrecognizable instruction code: " + String.format("%1$02X",code));
        byte reg = ram.getByte(address+1);
        byte reg1 = (byte)(reg>>4&0xf);
        byte reg2 = (byte)(reg&0xf);
        Short ramOffset = INSTRUCTION_LENGTHS[128 + code] == 4 ? ram.getShort(address + 2) : null;
        return constructor.create(reg1, reg2, ramOffset);
    }
}
