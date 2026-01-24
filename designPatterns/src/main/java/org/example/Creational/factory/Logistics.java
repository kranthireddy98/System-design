package org.example.Creational.factory;

public abstract  class Logistics {

    public void planDelivery()
    {

        //Call the factory method to create a Transport object.
        Transport transport= createTransport();

        //use the Product
        transport.deliver();
    }

    //This is the Factory Method
    public abstract Transport createTransport();
}
