package org.lldProblems.trafficsignalcontrolsystem.states.light;

import org.lldProblems.trafficsignalcontrolsystem.TrafficLight;
import org.lldProblems.trafficsignalcontrolsystem.enums.LightColor;

public class YellowState implements SignalState {
    @Override
    public void handle(TrafficLight context) {
        context.setColor(LightColor.YELLOW);
        context.setNextState(new RedState());
    }
}
