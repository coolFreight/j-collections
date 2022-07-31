package com.jcomm.threads.CompletableFuture;

import com.google.common.base.Suppliers;import com.jcomm.algorithms.SupplierHighTemperatureTask;

import java.util.concurrent.CompletableFuture;

/**
 * Created by jovaughnlockridge1 on 5/29/17.
 */
public class CompletionStageTests {

    public static void main (String a[]) throws InterruptedException {


        CompletableFuture<Integer> cf1 =  CompletableFuture.supplyAsync(new  SupplierHighTemperatureTask(45)).
                exceptionally(t -> {
//                    gradle bSystem.out.println("Something bad happened "+t);
                    return null;
                });


        cf1.thenComposeAsync( f1 -> {
            if(f1 >= 50 ){
                System.out.println("value is greater than 50 val is :"+f1);
            }else{
                System.out.println("value is less than 50 val is :"+f1);
            }

            CompletableFuture<Integer> completedFuture = new CompletableFuture<>();
            completedFuture.complete(f1);
            return completedFuture;
        });
        Thread.sleep(7000);
        //System.out.println(" finished completable future "+cf2.join());
    }
}
