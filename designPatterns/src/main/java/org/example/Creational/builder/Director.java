package org.example.Creational.builder;

public class Director {
    private final ComputerBuilder builder;

    public Director (ComputerBuilder builder){
        this.builder = builder;
    }
    public Computer construct(){
        builder.buildCpu();
        builder.buildRam();
        builder.buildStorage();
        return builder.getComputer();
    }
}
