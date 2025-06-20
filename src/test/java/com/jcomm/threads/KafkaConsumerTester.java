package com.jcomm.threads;

import java.util.concurrent.*;

import static java.lang.Thread.sleep;

public class KafkaConsumerTester {

    private final ExecutorService executor = Executors.newCachedThreadPool();

    CompletableFuture<String> completableFuture = new CompletableFuture<>();


    public void performTest() throws ExecutionException, InterruptedException, TimeoutException {
        executor.submit(() -> {
            try {
                sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            completableFuture.complete("hi there");
        });
        System.out.println("Waiting for result........");
        System.out.println(completableFuture.get(2_000, TimeUnit.SECONDS));
    }

    public void performTest(int time) throws ExecutionException, InterruptedException, TimeoutException {
        completableFuture.thenRunAsync(() -> System.out.println("doing another task"), executor);
        executor.submit(() -> {
            try {
                sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            completableFuture.complete("hi there");
        });
        System.out.println("Waiting for result........");
        System.out.println(completableFuture.get(time, TimeUnit.SECONDS));
    }

    public void shutDown() {
        executor.shutdown();
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        KafkaConsumerTester v = new KafkaConsumerTester();
        System.out.println("Starting test");
        v.performTest(20);

        v.shutDown();
        System.out.println("done test");
    }


}
