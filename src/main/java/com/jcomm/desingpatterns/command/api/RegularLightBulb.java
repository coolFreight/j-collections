package com.jcomm.desingpatterns.command.api;

public class RegularLightBulb implements Light {
    @Override
    public void on() {
        System.out.println("Light is on");
    }

    @Override
    public void off() {
        System.out.println("Light is off");
    }
}
