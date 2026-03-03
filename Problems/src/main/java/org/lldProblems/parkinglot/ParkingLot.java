package org.lldProblems.parkinglot;

import org.lldProblems.parkinglot.entities.ParkingFloor;
import org.lldProblems.parkinglot.entities.ParkingSpot;
import org.lldProblems.parkinglot.entities.ParkingTicket;
import org.lldProblems.parkinglot.strategy.fee.FeeStrategy;
import org.lldProblems.parkinglot.strategy.fee.FlatRateFeeStrategy;
import org.lldProblems.parkinglot.strategy.parking.BestFitStrategy;
import org.lldProblems.parkinglot.strategy.parking.ParkingStrategy;
import org.lldProblems.parkinglot.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLot {

    private static ParkingLot instance;

    private final List<ParkingFloor> floors = new ArrayList<>();
    private final Map<String, ParkingTicket> activeTickets;
    private FeeStrategy feeStrategy;
    private ParkingStrategy parkingStrategy;

    public ParkingLot() {
        this.feeStrategy = new FlatRateFeeStrategy();
        this.parkingStrategy=new BestFitStrategy();
        this.activeTickets = new ConcurrentHashMap<>();
    }

    public static synchronized ParkingLot getInstance(){
        if (instance==null){
            return instance=new ParkingLot();
        }

        return instance;
    }

    public void addFloor(ParkingFloor floor){
        floors.add(floor);
    }

    public void setFeeStrategy(FeeStrategy feeStrategy){
        this.feeStrategy = feeStrategy;
    }

    public Optional<ParkingTicket> parkVehicle(Vehicle vehicle){
        Optional<ParkingSpot> availableSpot = parkingStrategy.findSpot(floors,vehicle);

        if(availableSpot.isPresent()){
            ParkingSpot spot = availableSpot.get();
            spot.parkVehicle(vehicle);
            ParkingTicket ticket = new ParkingTicket(vehicle,spot);
            activeTickets.put(vehicle.getLicenceNumber(), ticket);
            System.out.printf("%s parked at %s. Ticket: %s\n", vehicle.getLicenceNumber(), spot.getSpotId(), ticket.getTicketId());
            return Optional.of(ticket);
        }
        System.out.println("No available spot for " + vehicle.getLicenceNumber());
        return Optional.empty();
    }

    public Optional<Double> unparkVehicle(String licenseNumber) {
        ParkingTicket ticket = activeTickets.remove(licenseNumber);
        if (ticket == null) {
            System.out.println("Ticket not found");
            return Optional.empty();
        }

        ticket.setExistTimestamp();
        ticket.getSpot().unParkVehicle();

        Double parkingFee = feeStrategy.calculateFee(ticket);

        return Optional.of(parkingFee);
    }
}
