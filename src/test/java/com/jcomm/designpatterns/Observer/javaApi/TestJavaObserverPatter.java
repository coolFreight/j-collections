package com.jcomm.designpatterns.Observer.javaApi;

/**
 * Created by jovaughnlockridge1 on 8/31/17.
 */
public class TestJavaObserverPatter {

    public static void main(String[] args) {
        WeatherData wd = new WeatherData();

        GeneralDisplay g = new GeneralDisplay();
        wd.addObserver(g);
    }
}
