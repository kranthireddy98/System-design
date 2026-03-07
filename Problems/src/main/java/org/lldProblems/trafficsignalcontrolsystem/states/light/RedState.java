package org.lldProblems.trafficsignalcontrolsystem.states.light;

import org.lldProblems.trafficsignalcontrolsystem.TrafficLight;
import org.lldProblems.trafficsignalcontrolsystem.enums.LightColor;

public class RedState implements SignalState{
    @Override
    public void handle(TrafficLight context) {
        context.setColor(LightColor.RED);

        context.setNextState(new RedState());
    }
}
