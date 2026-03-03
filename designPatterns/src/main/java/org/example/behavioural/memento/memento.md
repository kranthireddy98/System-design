The Memento Pattern is a behavioral design pattern that lets you capture and restore an objects internal state without violating 
encapsulation.

Save Object state now, and restore it later if needed.

### Real-World Analogy

**Undo in Text Editor**

When you;
* Type something
* Delete something
* Change formatting

You can press Undo and go back to the previous state.

* The Editor saves snapshot internally
* You don't see how it stores them.
* You just restore them.

That's exactly how memento works.

### Structure of memento Pattern
It has 3 roles:

1. Originator

The object whose state needs to be saved and restored.
2. Memento

Stores the internal state of the Originator.
3. Caretaker

Manages/saves the memento but does not modify it.

### Implementation 
1. Memento
```java
// Memento
public class EditorMemento {

    private final String content;

    public EditorMemento(String content) {
        this.content = content;
    }

    public String getSavedContent() {
        return content;
    }
}
```
Step 2: Originator
```java
// Originator
public class TextEditor {

    private String content;

    public void type(String text) {
        content = content + text;
    }

    public String getContent() {
        return content;
    }

    public EditorMemento save() {
        return new EditorMemento(content);
    }

    public void restore(EditorMemento memento) {
        content = memento.getSavedContent();
    }
}
```
Step 3: 
```java
import java.util.Stack;

// Caretaker
public class EditorHistory {

    private Stack<EditorMemento> history = new Stack<>();

    public void save(EditorMemento memento) {
        history.push(memento);
    }

    public EditorMemento undo() {
        return history.pop();
    }
}
```

Step 4: Client
```java
public class Main {

    public static void main(String[] args) {

        TextEditor editor = new TextEditor();
        EditorHistory history = new EditorHistory();

        editor.type("Hello ");
        history.save(editor.save());

        editor.type("World!");
        history.save(editor.save());

        System.out.println(editor.getContent()); 
        // Hello World!

        editor.restore(history.undo());
        System.out.println(editor.getContent()); 
        // Hello 
    }
}
```

### Where it is used in real systems
* Undo/Redo systems
* Database transaction rollback
* Game save checkpoints
* Workflow engines
* Version control snapshots

### Advantages
* Preserves encapsulation
* Simple rollback implementation
* Clean separation of concerns

### Disadvantages
* Can consume large memory if state is big
* Managing many snapshots can be expensive

### When to Use Memento?
Use it when:
* You need undo/rollback 
* You want to restore previous object states
* You don't want to expose internal state directly

### Memento Vs Other Patterns
| Pattern   | Purpose                         |
| --------- | ------------------------------- |
| Command   | Encapsulates requests           |
| State     | Changes behavior based on state |
| Memento   | Saves & restores state          |
| Prototype | Creates object copies           |

**Memento pattern captures and externalizes an object's internal state so that it can be
restored later without violating encapsulation.**

