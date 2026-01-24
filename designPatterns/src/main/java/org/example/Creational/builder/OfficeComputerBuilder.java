package org.example.Creational.builder;

public class OfficeComputerBuilder implements ComputerBuilder{
    private Computer computer = new Computer();
    @Override
    public void buildCpu() {
        computer.setCpu("Intel i5");
    }

    @Override
    public void buildRam() {
        computer.setRam("16GB");
    }

    @Override
    public void buildStorage() {
        computer.setStorage("512GB SSD");
    }

    @Override
    public Computer getComputer() {
        return computer;
    }
}
