package org.example.structural.decorator;

public class SugerDecorator extends CoffeeDecorator{

    public SugerDecorator(Coffee coffee){
        super(coffee);
    }
    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Sugar";
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 10;
    }
}
