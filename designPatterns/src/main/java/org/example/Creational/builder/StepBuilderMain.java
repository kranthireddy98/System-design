package org.example.Creational.builder;

public class StepBuilderMain {
    public static void main(String[] args) {
        Order order = Order.builder()
                .product("Laptop")
                .quantity(2)
                .address("HYD")
                .build();

        System.out.println(order);
    }
}
