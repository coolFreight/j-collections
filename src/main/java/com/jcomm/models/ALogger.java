package com.jcomm.models;

/**
 * Created by jovaughnlockridge1 on 8/20/17.
 */
public interface ALogger {

    String appendComma(String str);

    default void log(String str){
        System.out.println("ALogger Printing {} "+appendComma("jvoa"));
    }

    static boolean isNull(String str){
        System.out.println("Interface Null Check");
        return str == null ? true : "".equals(str) ? true : false;
    }

}
