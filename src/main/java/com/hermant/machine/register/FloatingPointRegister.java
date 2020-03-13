package com.hermant.machine.register;

import java.util.Random;

public final class FloatingPointRegister {

    public static final int REGISTER_SIZE = 16;

    private final float[] values;

    public FloatingPointRegister(){
        values = new float[REGISTER_SIZE];
        Random rand = new Random();
        for (int i = 0; i < REGISTER_SIZE; i++) {
            values[i] = Float.intBitsToFloat(rand.nextInt());
        }
    }

    public final float get(int i) {
        return values[i];
    }

    public final void set(int i, float value) {
        values[i] = value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Floating Point Register:\n" +
                "num |\tfloat value\t\t|\thex value\t|\tbit value\n");
        for (int i = 0; i < REGISTER_SIZE; i++) {
            float asFloat = get(i);
            int asInt = Float.floatToIntBits(asFloat);
            sb.append(i);
            sb.append("\t|\t");
            if(asFloat > 999999 || asFloat < -999999 || (asFloat < 0.000001f && asFloat > -0.000001f && asFloat!=0f))
                sb.append(String.format("%12.6e",asFloat));
            else
                sb.append(String.format("%12.6f",asFloat));
            sb.append("\t|\t");
            sb.append(String.format("%1$08X", asInt));
            sb.append("\t|\t");
            int leading = Integer.numberOfLeadingZeros(asInt);
            String binary = Integer.toBinaryString(asInt);
            int rest = 0;
            for(int j = 0; j < 32; j++){
                if(leading-->0) sb.append('0');
                else sb.append(binary.charAt(rest++));
                if(j == 0 | j == 8) sb.append(' ');
                if(j % 4 == 0) sb.append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
