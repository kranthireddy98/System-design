package org.lldProblems.parkinglot.entities;

import org.lldProblems.parkinglot.vehicle.Vehicle;

import java.util.Date;
import java.util.UUID;

public class ParkingTicket {
    private final String ticketId;
    private final Vehicle vehicle;
    private final ParkingSpot spot;
    private final long entryTimeStamp;
    private long existTimestamp;

    public ParkingTicket( Vehicle vehicle, ParkingSpot spot) {
        this.ticketId = UUID.randomUUID().toString();
        this.vehicle = vehicle;
        this.spot = spot;
        this.entryTimeStamp = new Date().getTime();
    }

    public String getTicketId() {
        return ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getSpot() {
        return spot;
    }

    public long getEntryTimeStamp() {
        return entryTimeStamp;
    }

    public long getExistTimestamp() {
        return existTimestamp;
    }

    public void setExistTimestamp() {
        this.existTimestamp = new Date().getTime();
    }
}
