package org.lldProblems.stackOverflow.observer;

import org.lldProblems.stackOverflow.entities.Event;

public interface PostObserver {
    void onPostEvent(Event event);
}
