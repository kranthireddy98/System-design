package org.example.Creational.abstractFactory;

public class Application {
    private final Button button;
    private final Checkbox checkbox;

    public Application(UIFactory factory){
        this.button = factory.createButton();
        this.checkbox = factory.createCheckBox();
    }

    void render(){
        button.paint();
        checkbox.paint();
    }
}
