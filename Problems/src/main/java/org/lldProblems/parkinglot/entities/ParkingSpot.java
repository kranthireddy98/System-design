package org.lldProblems.parkinglot.entities;

import org.lldProblems.parkinglot.vehicle.Vehicle;
import org.lldProblems.parkinglot.vehicle.VehicleSize;

public class ParkingSpot {
    private final String spotId;
    private Vehicle parkedvehicle;
    private boolean isOccupied;
    private final VehicleSize spotSize;


    public ParkingSpot(String spotId, VehicleSize spotSize) {
        this.spotId = spotId;
        this.spotSize = spotSize;
    }

    public synchronized void parkVehicle(Vehicle vehicle){
        this.parkedvehicle=vehicle;
        this.isOccupied=true;
    }

    public synchronized void unParkVehicle(){
        this.parkedvehicle=null;
        this.isOccupied=false;
    }

    public boolean canFitVehicle(Vehicle vehicle){
        if(isOccupied) return false;

        switch (vehicle.getSize()){
            case SMALL -> {
                return spotSize == VehicleSize.SMALL;
            }
            case MEDIUM -> {
                return spotSize == VehicleSize.MEDIUM;
            }
            case LARGE -> {
                return spotSize == VehicleSize.LARGE;
            }
            default -> {
                return false;
            }
        }
    }

    public synchronized boolean isAvailable(){
        return !isOccupied;
    }

    public String getSpotId() {
        return spotId;
    }

    public Vehicle getVehicle() {
        return parkedvehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.parkedvehicle = vehicle;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public VehicleSize getSpotSize() {
        return spotSize;
    }


}
