package org.example.factory;

public class SeaLogistics extends Logistics{

    public Transport createTransport()
    {
        return new Ship();
    }
}
