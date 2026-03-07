package org.lldProblems.trafficsignalcontrolsystem.observer;

import org.lldProblems.trafficsignalcontrolsystem.enums.Direction;
import org.lldProblems.trafficsignalcontrolsystem.enums.LightColor;

public class CentralMonitor implements TrafficObserver{

    @Override
    public void update(int intersectionId, Direction direction, LightColor color) {
        System.out.printf("[MONITOR] Intersection %d: Light for %s direction changed to %s. \n",
                intersectionId,direction,color);
    }
}
