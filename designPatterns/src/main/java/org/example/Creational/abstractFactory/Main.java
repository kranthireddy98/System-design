package org.example.Creational.abstractFactory;

public class Main {
    public static void main(String[] args) {
        UIFactory factory;

        String os = "WINDOWS";

        if(os.equalsIgnoreCase("WINDOWS")){
            factory = new WindowsUiFactory();
        }else {
            factory= new MacUIFactory();
        }

        Application app = new Application(factory);
        app.render();

    }
}
