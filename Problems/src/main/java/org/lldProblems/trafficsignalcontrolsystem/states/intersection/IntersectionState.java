package org.lldProblems.trafficsignalcontrolsystem.states.intersection;

import org.lldProblems.trafficsignalcontrolsystem.InterSectionController;

public interface IntersectionState {
    void handle(InterSectionController context) throws InterruptedException;
}
