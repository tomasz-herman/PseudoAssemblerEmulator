package com.hermant.machine.register;

import java.util.Random;

public final class GeneralPurposeRegister {

    public static final int REGISTER_SIZE = 16;
    public static final int REMAINDER = 8;
    public static final int POINTER = 9;
    public static final int STACK_FRAME_POINTER = 10;
    public static final int STACK_POINTER = 11;
    public static final int STACK_SECTION = 12;
    public static final int EXTRA_DATA_SECTION = 13;
    public static final int DATA_SECTION = 14;
    public static final int PROGRAM_SECTION = 15;

    private final int[] values;

    public GeneralPurposeRegister(){
        values = new int[REGISTER_SIZE];
        Random rand = new Random();
        for (int i = 0; i < REGISTER_SIZE; i++) {
            values[i] = rand.nextInt();
        }
    }

    public final int get(int i){
        return values[i];
    }

    public final void set(int i, int value){
        values[i] = value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("General Purpose Register:\n" +
                "num |\tinteger value\t|\tunsigned value\t|\thex value\t|\tbit value\n");
        for (int i = 0; i < REGISTER_SIZE; i++) {
            int asInt = get(i);
            sb.append(i);
            sb.append("\t|\t");
            sb.append(String.format("%12d", asInt));
            sb.append("\t|\t");
            sb.append(String.format("%12s", Integer.toUnsignedLong(asInt)));
            sb.append("\t|\t");
            sb.append(String.format("%1$08X", asInt));
            sb.append("\t|\t");
            int leading = Integer.numberOfLeadingZeros(asInt);
            String binary = Integer.toBinaryString(asInt);
            int rest = 0;
            for(int j = 0; j < 8; j++){
                for (int k = 0; k < 4; k++){
                    if(leading-->0) sb.append('0');
                    else sb.append(binary.charAt(rest++));
                }
                sb.append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}