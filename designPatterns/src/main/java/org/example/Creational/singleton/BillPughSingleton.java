package org.example.Creational.singleton;

public class BillPughSingleton {

    private  BillPughSingleton(){}

    private static class Instance {
        private static final BillPughSingleton instance = new BillPughSingleton();
    }

    public BillPughSingleton getInstance(){
        return Instance.instance;
    }
}
