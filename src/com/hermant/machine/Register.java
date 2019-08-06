package com.hermant.machine;

import java.util.Random;

public class Register{

    private static final int REGISTER_SIZE = 16;
    public static final int REMAINDER = 8;
    public static final int INSTRUCTION_POINTER = 9;
    public static final int STACK_FRAME_POINTER = 10;
    public static final int STACK_POINTER = 11;
    static final int STACK_SECTION = 12;
    static final int EXTRA_DATA_SECTION = 13;
    public static final int DATA_SECTION = 14;
    public static final int PROGRAM_SECTION = 15;

    private int[] values;

    Register(){
        values = new int[REGISTER_SIZE];
        Random rand = new Random();
        for (int i = 0; i < REGISTER_SIZE; i++) {
            values[i] = rand.nextInt();
        }
    }

    public int getInteger(int i){
        return values[i];
    }

    public float getFloat(int i){
        return Float.intBitsToFloat(getInteger(i));
    }

    public void setInteger(int i, int value){
        values[i] = value;
    }

    public void setFloat(int i, float value){
        values[i] = Float.floatToIntBits(value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("num |\tinteger value\t|\tfloat value\t\t|\thex value\t|\tbit value\n");
        for (int i = 0; i < REGISTER_SIZE; i++) {
            sb.append(i);
            sb.append("\t|\t");
            sb.append(String.format("%12d", getInteger(i)));
            sb.append("\t|\t");
            if(getFloat(i) > 999999 || getFloat(i) < -999999 || (getFloat(i) < 0.000001f && getFloat(i) > -0.000001f && getFloat(i)!=0f))
                sb.append(String.format("%12.6e",getFloat(i)));
            else
                sb.append(String.format("%12.6f",getFloat(i)));
            sb.append("\t|\t");
            sb.append(String.format("%1$08X",getInteger(i)));
            sb.append("\t|\t");
            int leading = Integer.numberOfLeadingZeros(getInteger(i));
            String binary = Integer.toBinaryString(getInteger(i));
            int rest = 0;
            for(int j = 0; j < 8; j++){
                for (int k = 0; k < 4; k++){
                    if(leading-->0) sb.append('0');
                    else sb.append(binary.charAt(rest++));
                }
                sb.append(' ');
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}