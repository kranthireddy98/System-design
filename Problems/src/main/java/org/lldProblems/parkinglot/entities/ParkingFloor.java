package org.lldProblems.parkinglot.entities;

import org.lldProblems.parkinglot.vehicle.Vehicle;
import org.lldProblems.parkinglot.vehicle.VehicleSize;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ParkingFloor {
    private final Map<String,ParkingSpot> spots;
    private final int floorNumber ;


    public ParkingFloor( int floorNumber) {
        this.spots = new ConcurrentHashMap<>();
        this.floorNumber = floorNumber;
    }

    public void addSpot(ParkingSpot spot){
        spots.put(spot.getSpotId(),spot);
    }

    public synchronized Optional<ParkingSpot> findAvailableSpot(Vehicle vehicle){
        return spots.values().stream()
                .filter(spot -> !spot.isOccupied() && spot.canFitVehicle(vehicle))
                .sorted(Comparator.comparing(ParkingSpot::getSpotSize))
                .findFirst();
    }

    public void displayAvailability(){
        System.out.printf("--- Floor %d Availability ---\n", floorNumber);

        Map<VehicleSize, Long> availableCounts = spots.values().stream()
                .filter(spot -> !spot.isOccupied())
                .collect(Collectors.groupingBy(ParkingSpot::getSpotSize,Collectors.counting()));

        for (VehicleSize size: VehicleSize.values()){
            System.out.printf("  %s spots: %d\n", size, availableCounts.getOrDefault(size, 0L));
        }
    }

}
