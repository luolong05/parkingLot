package org.oobootcamp.parkingLot;

import org.oobootcamp.parkingLot.exception.InvalidTicketException;
import org.oobootcamp.parkingLot.exception.ParkingLotIsFullException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class ParkingLot {
    private int carportCount;
    private Map<Ticket, Car> parkedCars;
    public ParkingLot(int carportCount) {
        this.carportCount = carportCount;
        this.parkedCars = new HashMap<>();
    }

    public Ticket park(Car car) throws Exception {
        if (parkedCars.size() >= this.carportCount) {
            throw new ParkingLotIsFullException();
        }

        Ticket ticket = new Ticket();
        this.parkedCars.put(ticket, car);
        return ticket;
    }

    public Car fetch(Ticket ticket) throws Exception {

        if (Objects.isNull(parkedCars.get(ticket))) {
            throw new InvalidTicketException();
        }

        return this.parkedCars.remove(ticket);
    }

    public boolean isAvailable() {
        return this.parkedCars.size() < this.carportCount;
    }

    public boolean hasCar(Ticket ticket) {
        return this.parkedCars.containsKey(ticket);
    }
}
