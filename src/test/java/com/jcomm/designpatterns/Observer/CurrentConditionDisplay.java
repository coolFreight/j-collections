package com.jcomm.designpatterns.Observer;

/**
 * Created by jovaughnlockridge1 on 8/31/17.
 */
public class CurrentConditionDisplay implements Observer, DisplayElement {
    private float temperature;
    private float humidity;
    private float pressure;
    private Subject weatherData;

    public CurrentConditionDisplay(Subject weatherData){
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        this.temperature = temp;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }

    @Override
    public void display() {

        System.out.println("Current condition: "+temperature+ "F degress and "+humidity+"% humidity");
    }
}
