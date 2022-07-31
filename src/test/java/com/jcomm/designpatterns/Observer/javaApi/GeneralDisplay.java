package com.jcomm.designpatterns.Observer.javaApi;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by jovaughnlockridge1 on 8/31/17.
 */
public class GeneralDisplay implements Observer {
    private float temperature;
    private float humidity;
    private float pressure;

    @Override
    public void update(Observable o, Object arg) {


    }
}
