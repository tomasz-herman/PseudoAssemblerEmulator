package com.hermant.machine;

import java.util.Random;

public class RandomNumberGenerator {
    private Random random = new Random();
    public int getNext(){
        return random.nextInt();
    }
}
