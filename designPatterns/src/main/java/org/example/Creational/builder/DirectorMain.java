package org.example.Creational.builder;

public class DirectorMain {
    public static void main(String[] args) {
        ComputerBuilder builder = new GamingComputerBuilder();

        Director director = new Director(builder);

        Computer computer = director.construct();

        System.out.println(computer);

        ComputerBuilder builder1 = new GamingComputerBuilder();
        builder1.buildCpu();
        Computer computer1 = builder1.getComputer();

        System.out.println(computer1);

        ComputerBuilder builder2 = new OfficeComputerBuilder();

        Director director1 = new Director(builder2);

        Computer computer2 = director.construct();
        System.out.println(computer2);

    }
}
