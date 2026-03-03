package org.example.behavioural.memento;

import java.util.Stack;

public class EditorHistory {

    private Stack<EditorMemento> history = new Stack<>();

    public void save(EditorMemento memento){
        history.push(memento);
    }

    public EditorMemento undo(){
        if (history.size() > 1){
            history.pop();
            return history.peek();
        }
       return history.peek();
    }
}
