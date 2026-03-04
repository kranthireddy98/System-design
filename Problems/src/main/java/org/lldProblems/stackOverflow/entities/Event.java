package org.lldProblems.stackOverflow.entities;

import org.lldProblems.stackOverflow.enums.EventType;

public class Event {
    private final EventType type;
    private final Post targetPost;
    private final User actor;

    public Event(EventType type, Post targetPost, User actor) {
        this.type = type;
        this.targetPost = targetPost;
        this.actor = actor;
    }

    public EventType getType() {
        return type;
    }

    public Post getTargetPost() {
        return targetPost;
    }

    public User getActor() {
        return actor;
    }
}
