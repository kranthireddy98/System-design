package org.example.behavioural.memento;

public class EditorMemento {

    private final String content;

    public EditorMemento(String content) {
        this.content = content;
    }

    public String getSavedContent(){
        return content;
    }


}
