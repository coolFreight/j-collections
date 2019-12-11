package com.jcomm.algorithms;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Created by jovaughnlockridge1 on 5/17/17.
 */
public class HighTemperatureTask implements Callable<Integer> {

    private final Integer highTemperature;

    public HighTemperatureTask(Integer highTemperature) {
        this.highTemperature = highTemperature;
    }

    @Override
    public Integer call() throws Exception {

        Random r = new Random();
        Integer responseTime = r.nextInt(30);
        System.out.println("Simulating response time of " + responseTime + " seconds");
        Thread.sleep(responseTime * 1_000);
        return highTemperature;
    }
}

