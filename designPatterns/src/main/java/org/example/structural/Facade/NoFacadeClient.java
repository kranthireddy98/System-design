package org.example.structural.Facade;

public class NoFacadeClient {

    public static void main(String[] args) {
        CPU cpu = new CPU();
        Memory memory = new Memory();
        HardDrive hardDrive = new HardDrive();

        cpu.start();
        memory.load();
        hardDrive.read();
    }
}
