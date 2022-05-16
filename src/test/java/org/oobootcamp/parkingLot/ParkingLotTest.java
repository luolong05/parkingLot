package org.oobootcamp.parkingLot;

import org.junit.jupiter.api.Test;
import org.oobootcamp.parkingLot.exception.InvalidTicketException;
import org.oobootcamp.parkingLot.exception.ParkingLotIsFullException;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    @Test
    void should_return_a_ticket_when_park_car_into_available_parking_lot() throws Exception {
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car();

        Ticket ticket = parkingLot.park(car);

        assertNotNull(ticket);
    }

    @Test
    void should_return_a_correct_car_when_fetch_given_a_ticket_and_car_is_parking_in_parking_lot() throws Exception {
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);

        Car fetchedCar = parkingLot.fetch(ticket);

        assertEquals(car, fetchedCar);
    }

    @Test
    void should_throw_exception_with_full_message_when_park_given_the_parking_lot_is_full() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(new Car());

        assertThrows(ParkingLotIsFullException.class, () -> parkingLot.park(new Car()));
    }

    @Test
    void should_throw_exception_with_invalid_ticket_message_when_fetch_given_ticket_and_the_parking_lot_is_empty() {
        Ticket invalidTicket = new Ticket();
        ParkingLot parkingLot = new ParkingLot(1);

        assertThrows(InvalidTicketException.class, () -> parkingLot.fetch(invalidTicket));
    }

    @Test
    void should_throw_exception_with_invalid_ticket_message_when_fetch_given_the_car_is_not_parked_in_the_parking_lot() throws Exception {
        Ticket invalidTicket = new Ticket();
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(new Car());

        assertThrows(InvalidTicketException.class, () -> parkingLot.fetch(invalidTicket));
    }

    @Test
    void should_return_a_ticket_when_park_given_a_full_parking_lot_and_fetched_a_car_and_a_car() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        Ticket ticket = parkingLot.park(new Car());
        parkingLot.fetch(ticket);

        Ticket newTicket = parkingLot.park(new Car());

        assertNotNull(newTicket);
    }
}
