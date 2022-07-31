package com.jcomm.desingpatterns.command.commands;

import com.jcomm.desingpatterns.command.api.Light;
import com.jcomm.desingpatterns.command.commands.Command;

public class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light){
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }
}
