package org.example.behavioural.memento;

public class TextEditor {
    private String content ="";

    public  void type(String text){
        content = content + text;
    }

    public String getContent(){
        return content;
    }

    public EditorMemento save(){
        return  new EditorMemento(content);
    }

    public void restore(EditorMemento memento){
        content = memento.getSavedContent();
    }
}
