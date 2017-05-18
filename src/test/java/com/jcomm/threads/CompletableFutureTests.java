package com.jcomm.threads;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;


import static java.util.concurrent.Executors.newCachedThreadPool;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by jovaughnlockridge1 on 3/13/17.
 */
public class CompletableFutureTests {

    private static ExecutorService service = newCachedThreadPool();
    private List<String> results = new ArrayList<>();

    @Test
    public void test_completed_future() throws Exception {
        String expectedValue = "the expected value";
        CompletableFuture<String> alreadyCompleted = CompletableFuture.completedFuture(expectedValue);
        assertThat(alreadyCompleted.get(), is(expectedValue));
    }


    @Test
    public void test_run_async() throws Exception {
        CompletableFuture<Void> runAsync = CompletableFuture.runAsync(() -> System.out.println("running async task"), service);
        pauseSeconds(1);
        assertThat(runAsync.isDone(), is(true));
    }

    @Test
    public void test_supply_async() throws Exception {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(simulatedTask(1, "Final Result"));
        assertThat(completableFuture.get(), is("Final Result"));
    }

    private ThrowingSupplier<String> simulatedTask(int numSeconds, String taskResult) {
        return () -> {
            Thread.sleep(numSeconds * 1000);
            return taskResult;
        };
    }

    @Test
    public void test_then_run_async() throws Exception {
        Map<String, String> cache = new HashMap<>();
        cache.put("key", "value");
        CompletableFuture<String> taskUsingCache =
                CompletableFuture.supplyAsync(simulatedTask(1, cache.get("key")));
        CompletableFuture<Void> cleanUp = taskUsingCache.thenRunAsync(cache::clear, service);
        cleanUp.get();
        String theValue = taskUsingCache.get();
        assertThat(cache.isEmpty(), is(true));
        assertThat(theValue, is("value"));
    }

    @Test
    public void test_accept_result() throws Exception {
        CompletableFuture<String> task = CompletableFuture.supplyAsync(simulatedTask(1, "add when done"), service);
        CompletableFuture<Void> acceptingTask = task.thenAccept(results::add);
        pauseSeconds(2);
        assertThat(acceptingTask.isDone(), is(true));
        assertThat(results.size(), is(1));
        assertThat(results.contains("add when done"), is(true));
    }

    @FunctionalInterface
    public interface ThrowingSupplier<U> extends Supplier<U> {

        @Override
        default U get() {
            try {
                return getThrows();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        U getThrows() throws Exception;
    }



    public void pauseSeconds(long seconds) {

        try{
            Thread.sleep(TimeUnit.SECONDS.toMillis(seconds));
        }catch (Exception e){
          e.printStackTrace();
        }
    }

}

