package com.jcomm.algorithms;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by jovaughnlockridge1 on 5/17/17.
 */
public class USDateServiceResponse {

    private final Future<Integer> f;

    public USDateServiceResponse (Future<Integer>  f){
        this.f = f;
    }

    public Integer getHighTemperature() {
        try {
            return f.get();
        }catch (ExecutionException | InterruptedException ee){
            System.out.println("Issue retrieving temperature "+ee.getMessage());
        }
        return null;
    }
}
