package org.example.behavioural.template;

public class Tea extends Beverage{

    @Override
    protected void addCondiments() {
        System.out.println("Steeping the tea");
    }

    @Override
    protected void brew() {
        System.out.println("Adding lemon");
    }
}
