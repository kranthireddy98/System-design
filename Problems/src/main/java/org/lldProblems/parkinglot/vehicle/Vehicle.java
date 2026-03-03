package org.lldProblems.parkinglot.vehicle;

public abstract class Vehicle {

    private final String licenceNumber;
    private final VehicleSize size;

    public Vehicle(String licenceNumber,VehicleSize size){
        this.licenceNumber=licenceNumber;
        this.size=size;
    }

    public String getLicenceNumber() {return licenceNumber;}

    public VehicleSize getSize(){
        return size;
    }

}
