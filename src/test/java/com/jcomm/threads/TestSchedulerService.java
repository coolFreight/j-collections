package com.jcomm.threads;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestSchedulerService {

    @Test
    public void testScheduleCreateNewTask() throws InterruptedException {
        ScheduledExecutorService s = new ScheduledThreadPoolExecutor(1);

        s.scheduleAtFixedRate(new Runner(),2, 2, TimeUnit.SECONDS);


        TimeUnit.SECONDS.sleep(10000);
    }


    private class Runner implements Runnable {
        private int timesCalled = 0;
        @Override
        public void run() {
            try {
                ++timesCalled;
                System.out.println("called "+this.toString()+" "+timesCalled+" times");
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


