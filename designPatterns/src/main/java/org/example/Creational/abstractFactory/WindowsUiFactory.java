package org.example.Creational.abstractFactory;

public class WindowsUiFactory implements UIFactory{


    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public Checkbox createCheckBox() {
        return new WindowsCheckbox();
    }
}
