package com.jcomm.whiteboard;

import java.util.stream.Stream;

/**
 * Created by jovaughnlockridge1 on 2/18/17.
 */
public class PrimeUtil {

    private long lastPrime = 0L;

    public long next() {
        lastPrime = next(lastPrime);
        return lastPrime;
    }

    public static long next(long after){
        long counter = after;

        while (!isPrime(++counter));

        return counter;
    }

    public static boolean isPrime(long number){
        if (number <= 1){
            return false;
        }

        if (number == 2){
            return true;
        }

        if (number % 2 ==0){
            return false;
        }

        long maxDivisor = (long) Math.sqrt(number);
        for (int counter = 3; counter <= maxDivisor; counter += 2) {
            if (number % counter == 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] a){
        Stream.iterate(0L, PrimeUtil::next).
//                skip(100).
                limit(5).
                forEach(System.out::println);
    }

}
