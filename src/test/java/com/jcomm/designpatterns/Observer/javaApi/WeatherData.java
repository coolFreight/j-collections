package com.jcomm.designpatterns.Observer.javaApi;

import java.util.Observable;

/**
 * Created by jovaughnlockridge1 on 8/31/17.
 */
public class WeatherData extends Observable {
    private float temp;
    private float humidity;
    private float pressure;

    public float getTemp() {
        return temp;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public void measurementChanged(float temp, float humidity, float pressure){
        this.temp = temp;
        this.humidity = humidity;
        this.pressure = pressure;
        notifyObservers();
    }
}
