package com.jcomm.models;

/**
 * Created by jovaughnlockridge1 on 8/20/17.
 */
public class LoggerImpl implements ALogger {

    @Override
    public String appendComma(String str) {
        return str+",";
    }

    public static void main(String[] args) {
        LoggerImpl logger = new LoggerImpl();
        logger.log("jova");
        ALogger.isNull("jova");
    }
}
