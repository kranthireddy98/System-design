package org.example.structural.decorator;

public class BasicCoffee implements Coffee{

    @Override
    public String getDescription(){
        return "Basic Coffee";
    }

    @Override
    public double getCost(){
        return 100;
    }
}
