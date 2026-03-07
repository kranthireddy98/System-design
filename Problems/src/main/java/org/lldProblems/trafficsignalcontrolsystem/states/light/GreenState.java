package org.lldProblems.trafficsignalcontrolsystem.states.light;

import org.lldProblems.trafficsignalcontrolsystem.TrafficLight;
import org.lldProblems.trafficsignalcontrolsystem.enums.LightColor;

public class GreenState implements SignalState{
    @Override
    public void handle(TrafficLight context) {
        context.setColor(LightColor.GREEN);

        context.setNextState(new YellowState());
    }
}
