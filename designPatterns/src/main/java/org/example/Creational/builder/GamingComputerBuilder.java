package org.example.Creational.builder;

public class GamingComputerBuilder implements ComputerBuilder{
    private Computer computer = new Computer();

    @Override
    public void buildCpu() {
        computer.setCpu("Intel i9");
    }

    @Override
    public void buildRam() {
        computer.setRam("32GB");
    }

    @Override
    public void buildStorage() {
        computer.setStorage("1TB SSD");
    }

    @Override
    public Computer getComputer() {
        return computer;
    }
}
