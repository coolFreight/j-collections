package com.jcomm.whiteboard;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class ExternalCall implements Runnable, Supplier<Void> {

    private final int simulateTimeTaken;

    public ExternalCall(int simulateTimeTaken) {
        this.simulateTimeTaken = simulateTimeTaken;
    }

    @Override
    public Void get() {
        var r =  new Random();
//        r.
        return null;
    }

    @Override
    public void run() {

        System.out.printf("Doing external call for %d%n", simulateTimeTaken);

        try {
            TimeUnit.SECONDS.sleep(simulateTimeTaken);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("External call completed");
    }
}
