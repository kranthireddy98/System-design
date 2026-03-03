package org.lldProblems.parkinglot.strategy.fee;

import org.lldProblems.parkinglot.entities.ParkingTicket;

public interface FeeStrategy {
    double calculateFee(ParkingTicket parkingTicket);
}
