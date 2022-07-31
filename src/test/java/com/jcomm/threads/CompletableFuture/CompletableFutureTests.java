package com.jcomm.threads.CompletableFuture;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Supplier;


import static java.util.concurrent.Executors.newCachedThreadPool;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by jovaughnlockridge1 on 3/13/17.
 */
public class CompletableFutureTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompletableFutureTests.class);

    private static ExecutorService service = newCachedThreadPool();
    private List<String> results = new ArrayList<>();

    @Test
    public void test_completed_future() throws Exception {
        String expectedValue = "the expected value";
        CompletableFuture<String> alreadyCompleted = CompletableFuture.completedFuture(expectedValue);
        assertThat(alreadyCompleted.get()).isEqualTo(expectedValue);
    }

    @Test
    public void test_completeable_task_cancel(){
        Future<String> task = new CompletableFuture<>();
        Executors.newCachedThreadPool().submit(() -> {
            System.out.println("Processing completable task cancel method");
            Thread.sleep(5000);
            task.cancel(false);
            System.out.println("Finished Processing completable task cancel method");
            return "Hello";
        });
        try {
            System.out.println("Getting result ");
            task.get();
        } catch (InterruptedException e) {
            System.err.println(" interrupt Exception ");
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.err.println(" execution Exception ");

            e.printStackTrace();
        }

    }

    @Test
    public void test_run_async() throws Exception {
        CompletableFuture<Void> runAsync = CompletableFuture.runAsync(() -> System.out.println("running async task"), service);
        pauseSeconds(1);
        assertThat(runAsync.isDone()).isTrue();
    }

    @Test
    public void test_supply_async() throws Exception {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(simulatedTask(1, "Final Result"));
        assertThat(completableFuture.get()).isEqualTo("Final Result");
    }

    private ThrowingSupplier<String> simulatedTask(int numSeconds, String taskResult) {
        return () -> {
            int x = 30;
            while( x-- > 0) {
                System.out.println("Thread for seconds " + numSeconds);
                Thread.sleep(numSeconds * 1000);
            }
            return taskResult;
        };
    }

    @Test
    public void test_then_run_async() throws Exception {
        Map<String, String> cache = new HashMap<>();
        cache.put("key", "value");
        CompletableFuture<String> taskUsingCache =
                CompletableFuture.supplyAsync(simulatedTask(10, cache.get("key")));
        CompletableFuture<String> taskUsingCache1 =
                CompletableFuture.supplyAsync(simulatedTask(15, cache.get("key")));
        CompletableFuture<Void> cleanUp = taskUsingCache.thenRunAsync(cache::clear, service);
        cleanUp.get();
        String theValue = taskUsingCache.get();
        assertThat(cache.isEmpty()).isTrue();
        assertThat(theValue).isEqualTo("value");
    }

    @Test
    public void test_accept_result() {
        CompletableFuture<String> task = CompletableFuture.supplyAsync(simulatedTask(1, "add when done"), service);
        CompletableFuture<Void> acceptingTask = task.thenAccept(results::add);
        pauseSeconds(2);
        assertThat(acceptingTask.isDone()).isTrue();
        assertThat(results.size()).isOne();
        assertThat(results.contains("add when done")).isTrue();
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


    @Test
    public void test_long_runningTask(){

        CompletableFuture.supplyAsync(() -> {
            Random r = new Random();
            Integer responseTime = r.nextInt(30);
              Integer val = 201;
            LOGGER.info("Simulating response time of {} seconds for value {}",responseTime , val);

            try {

                    Thread.sleep(responseTime * 1_000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                return val;
            }
        }).thenAccept( t -> LOGGER.info(t.toString(t)));

        try {
            Thread.sleep(31 * 1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

