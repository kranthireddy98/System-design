package org.example.Creational.builder;

public interface ComputerBuilder {
    void buildCpu();
    void buildRam();
    void buildStorage();

    Computer getComputer();

}
