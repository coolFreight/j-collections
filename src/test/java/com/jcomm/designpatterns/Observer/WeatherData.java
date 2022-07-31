package com.jcomm.designpatterns.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jovaughnlockridge1 on 8/31/17.
 */
public class WeatherData implements Subject<Observer> {

    private List<Observer> observerList = new ArrayList<>();
    private float temp;
    private float humidity;
    private float pressure;

    @Override
    public void registerObserver(Observer o) {
        observerList.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observerList.remove(0);
    }

    @Override
    public void notifyObservers() {
        observerList.stream().forEach(t-> t.update(temp, humidity, pressure));
    }

    public void measurementChanged(float temp, float humidity, float pressure){
        this.temp = temp;
        this.humidity = humidity;
        this.pressure = pressure;
        notifyObservers();
    }
}
