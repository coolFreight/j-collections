package com.jcomm.desingpatterns.command;

import com.jcomm.desingpatterns.command.api.GarageDoor;
import com.jcomm.desingpatterns.command.api.Light;
import com.jcomm.desingpatterns.command.api.RegularLightBulb;
import com.jcomm.desingpatterns.command.commands.GarageDoorOpenCommand;
import com.jcomm.desingpatterns.command.commands.LightOnCommand;
import com.jcomm.desingpatterns.command.remotecontrol.SimpleRemoteControl;

public class RemoteControlTest {
    public static void main(String[] args) {
        SimpleRemoteControl remote = new SimpleRemoteControl();
        Light light = new RegularLightBulb();
        LightOnCommand lightOn = new LightOnCommand(light);
        GarageDoor garageDoor = new GarageDoor();
        GarageDoorOpenCommand garageDoorOpenCommand = new GarageDoorOpenCommand(garageDoor);

        remote.setCommand(lightOn);
        remote.buttonWasPressed();
        remote.setCommand(garageDoorOpenCommand);
        remote.buttonWasPressed();
    }
}
