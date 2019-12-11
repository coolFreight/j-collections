package com.jcomm.algorithms;

import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by jovaughnlockridge1 on 5/16/17.
 */
public class USDateService implements DateService {

    private final BlockingQueue<Integer> q = new ArrayBlockingQueue<>(4);
    ExecutorService ses = Executors.newFixedThreadPool(4);


    public void startService(){

        new Thread(() -> {
            while (true) {
                try {
                    Random random = new Random();
                    Integer i = random.nextInt(140);
                    q.put(i);
                }catch(InterruptedException ioe){
                    System.exit(-1);
                }
            }
        }).start();
    }

    @Override
    public USDateServiceResponse getTemperatureHigh(LocalDate date) {
        Future<Integer> f = ses.submit( new HighTemperatureTask(q.poll()));
        return new USDateServiceResponse(f);
    }

    public static void main (String a[]) throws InterruptedException {

        USDateService s = new USDateService();
        s.startService();

        Thread.sleep(1000);

        System.out.println(s.getTemperatureHigh(LocalDate.now()).getHighTemperature()+ " degrees");
        System.out.println(s.getTemperatureHigh(LocalDate.now()).getHighTemperature()+ " degrees");
        System.out.println(s.getTemperatureHigh(LocalDate.now()).getHighTemperature()+ " degrees");
        System.out.println(s.getTemperatureHigh(LocalDate.now()).getHighTemperature()+ " degrees");
        System.out.println(s.getTemperatureHigh(LocalDate.now()).getHighTemperature()+ " degrees");
        System.out.println(s.getTemperatureHigh(LocalDate.now()).getHighTemperature()+ " degrees");
        System.out.println(s.getTemperatureHigh(LocalDate.now()).getHighTemperature()+ " degrees");
        System.out.println(s.getTemperatureHigh(LocalDate.now()).getHighTemperature()+ " degrees");
        System.out.println(s.getTemperatureHigh(LocalDate.now()).getHighTemperature()+ " degrees");
        System.out.println(s.getTemperatureHigh(LocalDate.now()).getHighTemperature()+ " degrees");
        System.out.println(s.getTemperatureHigh(LocalDate.now()).getHighTemperature()+ " degrees");
        System.out.println(s.getTemperatureHigh(LocalDate.now()).getHighTemperature()+ " degrees");

    }
}
