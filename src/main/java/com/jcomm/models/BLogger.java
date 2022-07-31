package com.jcomm.models;

/**
 * Created by jovaughnlockridge1 on 8/20/17.
 */
public interface BLogger {

    default void log(String str){
        System.out.println("BLogger at work");
    }
}
