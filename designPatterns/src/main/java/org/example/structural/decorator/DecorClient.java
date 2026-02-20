package org.example.structural.decorator;

public class DecorClient {

    public static void main(String[] args) {
        Coffee coffee = new BasicCoffee();

        coffee = new MilkDecorator(coffee);

        coffee = new SugerDecorator(coffee);

        System.out.println(coffee.getDescription());
        System.out.println(coffee.getCost());
    }
}
