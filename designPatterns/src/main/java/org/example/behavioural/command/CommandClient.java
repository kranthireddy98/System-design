package org.example.behavioural.command;

public class CommandClient {
    public static void main(String[] args) {
        Light light = new Light();

        Command turOn = new TurnOnCommand(light);
        RemoteController remote = new RemoteController();
        remote.setCommand(turOn);

        remote.pressButton();

        Command turnOff = new TurnOffCommand(light);
        remote.setCommand(turnOff);
        remote.pressButton();
    }
}
