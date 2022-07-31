package com.jcomm.algorithms;

import java.util.Random;
import java.util.function.Supplier;

/**
 * Created by jovaughnlockridge1 on 5/29/17.
 */
public class SupplierHighTemperatureTask implements Supplier<Integer> {

    private final Integer highTemperature;

    public SupplierHighTemperatureTask(Integer highTemperature){
        this.highTemperature = highTemperature;
    }


    @Override
    public Integer get() {
        Random r = new Random();
        Integer responseTime = r.nextInt(6);
        System.out.println("Simulating response time of " + responseTime + " seconds"+" for value "+highTemperature);

        if( responseTime % 2 == 0){
            throw new RuntimeException("Even Number");
        }

        try {
            Thread.sleep(responseTime * 1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return highTemperature;
    }
}
