package org.example.factory;

public class Ship implements Transport{

    @Override
    public void deliver() {
        System.out.println("Delivering by sea in a container ship");

    }

}
