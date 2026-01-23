package org.example.factory;

public class RoadLogistics extends Logistics{

    public  Transport createTransport()
    {
        return new Truck();
    }
}
