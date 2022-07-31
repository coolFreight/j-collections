package com.jcomm.desingpatterns.command.remotecontrol;

import com.jcomm.desingpatterns.command.commands.Command;

public class SimpleRemoteControl {
    private Command slot;

    public SimpleRemoteControl(){
    }

    public void setCommand(Command command){
        slot = command;
    }

    public void buttonWasPressed() {
        slot.execute();
    }
}
