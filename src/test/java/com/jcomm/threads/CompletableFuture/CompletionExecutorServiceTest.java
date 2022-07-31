package com.jcomm.threads.CompletableFuture;

import com.jcomm.algorithms.HighTemperatureTask;
import com.jcomm.algorithms.USDateServiceResponse;
import org.apache.avro.generic.GenericData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.*;

/**
 * Created by jovaughnlockridge1 on 5/22/17.
 */
public class CompletionExecutorServiceTest {


    public static void main(String a[]){


        Collection<HighTemperatureTask> tasks = new ArrayList<>();
        tasks.add(new HighTemperatureTask(125));
        tasks.add(new HighTemperatureTask(2));
        tasks.add(new HighTemperatureTask(1253));
        tasks.add(new HighTemperatureTask(232));

        Executor e = Executors.newFixedThreadPool(3);

        ExecutorCompletionService<Integer> ecs = new ExecutorCompletionService<>(e);
        tasks.stream().forEach( t -> ecs.submit(t));

        try {
            Future<Integer> f1 = ecs.take();
            System.out.println("first future is ready "+f1.get());
            Future<Integer> f2 = ecs.take();
            System.out.println("second future is ready "+f2.get());
            Future<Integer> f3 = ecs.take();
            System.out.println("third future is ready "+f3.get());
            Future<Integer> f4 = ecs.take();
            System.out.println("fourth future is ready "+f4.get());
        }catch (InterruptedException | ExecutionException ie){
            ie.printStackTrace();
        }
    }
}
