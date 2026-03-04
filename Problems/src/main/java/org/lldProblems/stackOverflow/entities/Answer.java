package org.lldProblems.stackOverflow.entities;

import java.util.UUID;

public class Answer extends Post{

    private  boolean isAccepted = false;


    public Answer( String body, User author) {
        super(UUID.randomUUID().toString(), body, author);
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }
}
