package org.example.behavioural.memento;

public class MementoClient {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        EditorHistory history = new EditorHistory();

        editor.type("Hello ");
        history.save(editor.save());

        editor.type("World");
        history.save(editor.save());

        System.out.println(editor.getContent());

        editor.restore(history.undo());

        System.out.println(editor.getContent());
    }
}
