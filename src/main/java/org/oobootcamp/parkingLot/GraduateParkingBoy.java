package org.oobootcamp.parkingLot;

import org.oobootcamp.parkingLot.exception.InvalidTicketException;
import org.oobootcamp.parkingLot.exception.ParkingLotIsFullException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class GraduateParkingBoy {

    private List<ParkingLot> parkingLots;

    public GraduateParkingBoy(ParkingLot... parkingLots) {

        this.parkingLots = List.of(parkingLots);
    }

    public Ticket park(Car car) throws Exception {
        Optional<ParkingLot> availableParkingLot = this.parkingLots.stream().filter(ParkingLot::isAvailable).findFirst();

        if (availableParkingLot.isEmpty()) {
            throw new ParkingLotIsFullException();
        }

        return availableParkingLot.get().park(car);
    }

    public Car fetch(Ticket ticket) throws Exception {
        Optional<ParkingLot> parkingLotHasTargetCar = this.parkingLots.stream().filter(parkingLot -> parkingLot.hasCar(ticket)).findAny();

        if (parkingLotHasTargetCar.isEmpty()) {
            throw new InvalidTicketException();
        }

        return parkingLotHasTargetCar.get().fetch(ticket);
    }
}
