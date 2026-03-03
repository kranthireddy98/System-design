package org.lldProblems.parkinglot.strategy.parking;

import org.lldProblems.parkinglot.entities.ParkingFloor;
import org.lldProblems.parkinglot.entities.ParkingSpot;
import org.lldProblems.parkinglot.vehicle.Vehicle;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ParkingStrategy {

    Optional<ParkingSpot> findSpot(List<ParkingFloor> floors, Vehicle vehicle);
}
