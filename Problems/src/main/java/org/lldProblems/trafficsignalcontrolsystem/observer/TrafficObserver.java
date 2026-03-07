package org.lldProblems.trafficsignalcontrolsystem.observer;

import org.lldProblems.trafficsignalcontrolsystem.enums.Direction;
import org.lldProblems.trafficsignalcontrolsystem.enums.LightColor;

public interface TrafficObserver {
    void update(int intersectionId, Direction direction, LightColor color);
}
