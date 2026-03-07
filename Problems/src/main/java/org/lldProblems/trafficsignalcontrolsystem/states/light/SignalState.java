package org.lldProblems.trafficsignalcontrolsystem.states.light;

import org.lldProblems.trafficsignalcontrolsystem.TrafficLight;

public interface SignalState {
    void handle(TrafficLight context);
}
