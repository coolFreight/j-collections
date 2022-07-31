package com.jcomm.designpatterns.Observer;

/**
 * Created by jovaughnlockridge1 on 8/31/17.
 */
public class WeatherStation {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        CurrentConditionDisplay currentConditionDisplay = new CurrentConditionDisplay(weatherData);


        weatherData.measurementChanged(80, 65, 30.4f);
        weatherData.measurementChanged(82, 70, 29.2f);
        weatherData.measurementChanged(78, 90, 29.2f);
    }
}
