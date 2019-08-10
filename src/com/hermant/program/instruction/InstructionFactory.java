package com.hermant.program.instruction;

import com.hermant.machine.RandomAccessMemory;
import com.hermant.machine.Register;

import static com.hermant.machine.Register.INSTRUCTION_POINTER;
import static com.hermant.program.instruction.Instruction.*;

public abstract class InstructionFactory {

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
        switch(code) {
            case RETURN -> {return new ReturnInstruction(reg1, reg2, ramOffset);}
            case LOAD -> {return new LoadInstruction(reg1, reg2, ramOffset);}
            case LOAD_REGISTER -> {return new LoadRegisterInstruction(reg1, reg2, ramOffset);}
            case STORE -> {return new StoreInstruction(reg1, reg2, ramOffset);}
            case LOAD_FLOAT -> {return new LoadFloatInstruction(reg1, reg2, ramOffset);}
            case LOAD_REGISTER_FLOAT -> {return new LoadRegisterFloatInstruction(reg1, reg2, ramOffset);}
            case STORE_FLOAT -> {return new StoreFloatInstruction(reg1, reg2, ramOffset);}
            case LOAD_ADDRESS -> {return new LoadAddressInstruction(reg1, reg2, ramOffset);}
            case EXCHANGE -> {return new ExchangeInstruction(reg1, reg2, ramOffset);}
            case EXCHANGE_REGISTER -> {return new ExchangeRegisterInstruction(reg1, reg2, ramOffset);}
            case EXCHANGE_FLOAT -> {return new ExchangeFloatInstruction(reg1, reg2, ramOffset);}
            case EXCHANGE_REGISTER_FLOAT ->{return new ExchangeRegisterFloatInstruction(reg1, reg2, ramOffset);}
            case STORE_BYTE -> {return new StoreByteInstruction(reg1, reg2, ramOffset);}
            case LOAD_BYTE_UNSIGNED -> {return new LoadByteUnsignedInstruction(reg1, reg2, ramOffset);}
            case LOAD_BYTE -> {return new LoadByteInstruction(reg1, reg2, ramOffset);}
            case LOAD_INTEGER_AS_FLOAT -> {return new LoadIntegerAsFloatInstruction(reg1, reg2, ramOffset);}
            case STORE_FLOAT_AS_INTEGER -> {return new StoreFloatAsIntegerInstruction(reg1, reg2, ramOffset);}
            case RANDOM -> {return new RandomInstruction(reg1, reg2, ramOffset);}
            case RANDOM_REGISTER -> {return new RandomRegisterInstruction(reg1, reg2, ramOffset);}
            case ENTER -> {return new EnterInstruction(reg1, reg2, ramOffset);}
            case LEAVE -> {return new LeaveInstruction(reg1, reg2, ramOffset);}
            case OUTPUT -> {return new OutputInstruction(reg1, reg2, ramOffset);}
            case OUTPUT_SIGNED -> {return new OutputSignedInstruction(reg1, reg2, ramOffset);}
            case OUTPUT_FLOAT -> {return new OutputFloatInstruction(reg1, reg2, ramOffset);}
            case OUTPUT_BYTE -> {return new OutputByteInstruction(reg1, reg2, ramOffset);}
            case OUTPUT_CHAR -> {return new OutputCharInstruction(reg1, reg2, ramOffset);}
            case OUTPUT_REGISTER -> {return new OutputRegisterInstruction(reg1, reg2, ramOffset);}
            case OUTPUT_REGISTER_SIGNED -> {return new OutputRegisterSignedInstruction(reg1, reg2, ramOffset);}
            case OUTPUT_REGISTER_FLOAT -> {return new OutputRegisterFloatInstruction(reg1, reg2, ramOffset);}
            case OUTPUT_REGISTER_BYTE -> {return new OutputRegisterByteInstruction(reg1, reg2, ramOffset);}
            case OUTPUT_REGISTER_CHAR -> {return new OutputRegisterCharInstruction(reg1, reg2, ramOffset);}
            case NO_OPERATION -> {return new NoOperationInstruction(reg1, reg2, ramOffset);}

            case PUSH -> {return new PushInstruction(reg1, reg2, ramOffset);}
            case PUSH_REGISTER -> {return new PushRegisterInstruction(reg1, reg2, ramOffset);}
            case PUSH_REGISTER_FLOAT -> {return new PushRegisterFloatInstruction(reg1, reg2, ramOffset);}
            case PUSH_FLAGS -> {return new PushFlagsInstruction(reg1, reg2, ramOffset);}
            case POP -> {return new PopInstruction(reg1, reg2, ramOffset);}
            case POP_REGISTER -> {return new PopRegisterInstruction(reg1, reg2, ramOffset);}
            case POP_REGISTER_FLOAT -> {return new PopRegisterFloatInstruction(reg1, reg2, ramOffset);}
            case POP_FLAGS -> {return new PopFlagsInstruction(reg1, reg2, ramOffset);}

            case ADD -> {return new AddInstruction(reg1, reg2, ramOffset);}
            case ADD_REGISTER -> {return new AddRegisterInstruction(reg1, reg2, ramOffset);}
            case SUBTRACT -> {return new SubtractInstruction(reg1, reg2, ramOffset);}
            case SUBTRACT_REGISTER -> {return new SubtractRegisterInstruction(reg1, reg2, ramOffset);}
            case MULTIPLY -> {return new MultiplyInstruction(reg1, reg2, ramOffset);}
            case MULTIPLY_REGISTER -> {return new MultiplyRegisterInstruction(reg1, reg2, ramOffset);}
            case DIVIDE -> {return new DivideInstruction(reg1, reg2, ramOffset);}
            case DIVIDE_REGISTER -> {return new DivideRegisterInstruction(reg1, reg2, ramOffset);}
            case DIVIDE_SIGNED -> {return new DivideSignedInstruction(reg1, reg2, ramOffset);}
            case DIVIDE_SIGNED_REGISTER -> {return new DivideSignedRegisterInstruction(reg1, reg2, ramOffset);}
            case COMPARE -> {return new CompareInstruction(reg1, reg2, ramOffset);}
            case COMPARE_REGISTER -> {return new CompareRegisterInstruction(reg1, reg2, ramOffset);}

            case COMPARE_FLOAT -> {return new CompareFloatInstruction(reg1, reg2, ramOffset);}
            case COMPARE_REGISTER_FLOAT -> {return new CompareRegisterFloatInstruction(reg1, reg2, ramOffset);}

            case ADD_FLOAT -> {return new AddFloatInstruction(reg1, reg2, ramOffset);}
            case ADD_REGISTER_FLOAT -> {return new AddRegisterFloatInstruction(reg1, reg2, ramOffset);}
            case SUBTRACT_FLOAT -> {return new SubtractFloatInstruction(reg1, reg2, ramOffset);}
            case SUBTRACT_REGISTER_FLOAT -> {return new SubtractRegisterFloatInstruction(reg1, reg2, ramOffset);}
            case MULTIPLY_FLOAT -> {return new MultiplyFloatInstruction(reg1, reg2, ramOffset);}
            case MULTIPLY_REGISTER_FLOAT -> {return new MultiplyRegisterFloatInstruction(reg1, reg2, ramOffset);}
            case DIVIDE_FLOAT -> {return new DivideFloatInstruction(reg1, reg2, ramOffset);}
            case DIVIDE_REGISTER_FLOAT -> {return new DivideRegisterFloatInstruction(reg1, reg2, ramOffset);}
            case SQUARE_ROOT_FLOAT -> {return new SquareRootFloatInstruction(reg1, reg2, ramOffset);}
            case ABSOLUTE_FLOAT -> {return new AbsoluteFloatInstruction(reg1, reg2, ramOffset);}
            case SINE_FLOAT -> {return new SineFloatInstruction(reg1, reg2, ramOffset);}
            case COSINE_FLOAT -> {return new CosineFloatInstruction(reg1, reg2, ramOffset);}
            case TANGENT_FLOAT -> {return new TangentFloatInstruction(reg1, reg2, ramOffset);}
            case EXAMINE_FLOAT -> {return new ExamineFloatInstruction(reg1, reg2, ramOffset);}
            case EXAMINE_FLOAT_REGISTER -> {return new ExamineFloatRegisterInstruction(reg1, reg2, ramOffset);}
            case TEST_FLOAT -> {return new TestFloatInstruction(reg1, reg2, ramOffset);}
            case TEST_FLOAT_REGISTER -> {return new TestFloatRegisterInstruction(reg1, reg2, ramOffset);}

            case NEGATE -> {return new NegateInstruction(reg1, reg2, ramOffset);}
            case NEGATE_REGISTER -> {return new NegateRegisterInstruction(reg1, reg2, ramOffset);}
            case INCREMENT -> {return new IncrementInstruction(reg1, reg2, ramOffset);}
            case INCREMENT_REGISTER -> {return new IncrementRegisterInstruction(reg1, reg2, ramOffset);}
            case DECREMENT -> {return new DecrementInstruction(reg1, reg2, ramOffset);}
            case DECREMENT_REGISTER -> {return new DecrementRegisterInstruction(reg1, reg2, ramOffset);}

            case TEST -> {return new TestInstruction(reg1, reg2, ramOffset);}
            case TEST_REGISTER -> {return new TestRegisterInstruction(reg1, reg2, ramOffset);}
            case AND -> {return new AndInstruction(reg1, reg2, ramOffset);}
            case AND_REGISTER -> {return new AndRegisterInstruction(reg1, reg2, ramOffset);}
            case OR -> {return new OrInstruction(reg1, reg2, ramOffset);}
            case OR_REGISTER -> {return new OrRegisterInstruction(reg1, reg2, ramOffset);}
            case XOR -> {return new XorInstruction(reg1, reg2, ramOffset);}
            case XOR_REGISTER -> {return new XorRegisterInstruction(reg1, reg2, ramOffset);}
            case RIGHT_SHIFT_LOGICAL -> {return new RightShiftLogicalInstruction(reg1, reg2, ramOffset);}
            case LEFT_SHIFT_LOGICAL -> {return new LeftShiftLogicalInstruction(reg1, reg2, ramOffset);}
            case RIGHT_SHIFT_LOGICAL_REGISTER -> {return new RightShiftLogicalRegisterInstruction(reg1, reg2, ramOffset);}
            case LEFT_SHIFT_LOGICAL_REGISTER -> {return new LeftShiftLogicalRegisterInstruction(reg1, reg2, ramOffset);}
            case RIGHT_SHIFT_ARITHMETIC -> {return new RightShiftArithmeticInstruction(reg1, reg2, ramOffset);}
            case RIGHT_SHIFT_ARITHMETIC_REGISTER -> {return new RightShiftArithmeticRegisterInstruction(reg1, reg2, ramOffset);}
            case RIGHT_ROTATE -> {return new RightRotateInstruction(reg1, reg2, ramOffset);}
            case LEFT_ROTATE -> {return new LeftRotateInstruction(reg1, reg2, ramOffset);}
            case RIGHT_ROTATE_REGISTER -> {return new RightRotateRegisterInstruction(reg1, reg2, ramOffset);}
            case LEFT_ROTATE_REGISTER -> {return new LeftRotateRegisterInstruction(reg1, reg2, ramOffset);}

            case NOT_REGISTER -> {return new NotRegisterInstruction(reg1, reg2, ramOffset);}
            case NOT -> {return new NotInstruction(reg1, reg2, ramOffset);}

            case JUMP -> {return new JumpInstruction(reg1, reg2, ramOffset);}
            case JUMP_EQUAL -> {return new JumpEqualInstruction(reg1, reg2, ramOffset);}
            case JUMP_NOT_EQUAL -> {return new JumpNotEqualInstruction(reg1, reg2, ramOffset);}
            case JUMP_GREATER -> {return new JumpGreaterInstruction(reg1, reg2, ramOffset);}
            case JUMP_GREATER_OR_EQUAL -> {return new JumpGreaterOrEqualInstruction(reg1, reg2, ramOffset);}
            case JUMP_LESSER -> {return new JumpLesserInstruction(reg1, reg2, ramOffset);}
            case JUMP_LESS_OR_EQUAL -> {return new JumpLessOrEqualInstruction(reg1, reg2, ramOffset);}
            case JUMP_ABOVE -> {return new JumpAboveInstruction(reg1, reg2, ramOffset);}
            case JUMP_ABOVE_OR_EQUAL -> {return new JumpAboveOrEqualInstruction(reg1, reg2, ramOffset);}
            case JUMP_BELOW -> {return new JumpBelowInstruction(reg1, reg2, ramOffset);}
            case JUMP_BELOW_OR_EQUAL -> {return new JumpBelowOrEqualInstruction(reg1, reg2, ramOffset);}
            case JUMP_OVERFLOW ->{return new JumpOverflowInstruction(reg1, reg2, ramOffset);}
            case JUMP_NOT_OVERFLOW -> {return new JumpNotOverflowInstruction(reg1, reg2, ramOffset);}
            case JUMP_SIGNED -> {return new JumpSignedInstruction(reg1, reg2, ramOffset);}
            case JUMP_NOT_SIGNED -> {return new JumpNotSignedInstruction(reg1, reg2, ramOffset);}
            case JUMP_PARITY -> {return new JumpParityInstruction(reg1, reg2, ramOffset);}
            case JUMP_NOT_PARITY -> {return new JumpNotParityInstruction(reg1, reg2, ramOffset);}
            case LOOP -> {return new LoopInstruction(reg1, reg2, ramOffset);}
            case CALL -> {return new CallInstruction(reg1, reg2, ramOffset);}

            default -> throw new IllegalStateException("Unrecognizable instruction code " + String.format("%1$02X",code));
        }
    }
}
