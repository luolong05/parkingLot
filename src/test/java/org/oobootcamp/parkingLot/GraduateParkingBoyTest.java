package org.oobootcamp.parkingLot;

import org.junit.jupiter.api.Test;
import org.oobootcamp.parkingLot.exception.InvalidTicketException;
import org.oobootcamp.parkingLot.exception.ParkingLotIsFullException;

import static org.junit.jupiter.api.Assertions.*;

public class GraduateParkingBoyTest {

    @Test
    void should_return_ticket_when_park_given_parking_boy_has_one_parking_lot_has_car_pot() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLot);
        Car car = new Car();

        Ticket ticket = graduateParkingBoy.park(car);

        assertNotNull(ticket);
    }

    @Test
    void should_return_a_car_when_fetch_given_parked_one_car_parking_lot_and_a_ticket() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLot);
        Car car = new Car();
        Ticket ticket = graduateParkingBoy.park(car);

        Car fetchedCar = graduateParkingBoy.fetch(ticket);

        assertEquals(car, fetchedCar);
    }

    @Test
    void should_return_correct_cars_when_fetch_given_parked_two_cars_parking_lot_and_two_tickets() throws Exception {
        ParkingLot parkingLot = new ParkingLot(2);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLot);
        Car car1 = new Car();
        Car car2 = new Car();
        Ticket ticket1 = graduateParkingBoy.park(car1);
        Ticket ticket2 = graduateParkingBoy.park(car2);

        Car fetchedCar1 = graduateParkingBoy.fetch(ticket1);
        Car fetchedCar2 = graduateParkingBoy.fetch(ticket2);

        assertEquals(car1, fetchedCar1);
        assertEquals(car2, fetchedCar2);
    }

    @Test
    void should_throw_parking_lot_is_full_message_when_park_given_a_full_parking_lot() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLot);
        graduateParkingBoy.park(new Car());

        assertThrows(ParkingLotIsFullException.class,  () -> graduateParkingBoy.park(new Car()));
    }

    @Test
    void should_throw_invalid_ticket_exception_when_fetch_given_parking_lot_with_a_car_and_invalid_ticket() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLot);
        graduateParkingBoy.park(new Car());

        assertThrows(InvalidTicketException.class,  () -> graduateParkingBoy.fetch(new Ticket()));
    }

    @Test
    void should_throw_invalid_ticket_exception_when_fetch_given_parking_lot_without_my_car_and_used_ticket() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLot);
        Ticket ticket = graduateParkingBoy.park(new Car());
        graduateParkingBoy.fetch(ticket);

        assertThrows(InvalidTicketException.class,  () -> graduateParkingBoy.fetch(ticket));
    }

    @Test
    void should_return_a_ticket_and_park_car_at_first_parking_lot_when_park_given_two_available_parking_lots() throws Exception {
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLot1, parkingLot2);
        Car car = new Car();

        Ticket ticket = graduateParkingBoy.park(car);

        assertNotNull(ticket);
        assertEquals(car, parkingLot1.fetch(ticket));
        assertThrows(InvalidTicketException.class, () -> parkingLot2.fetch(ticket));
    }

    @Test
    void should_return_a_ticket_and_park_car_at_second_parking_lot_when_park_given_first_parking_lot_is_full_and_second_is_available() throws Exception {
        ParkingLot parkingLot1 = new ParkingLot(1);
        parkingLot1.park(new Car());
        ParkingLot parkingLot2 = new ParkingLot(1);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLot1, parkingLot2);
        Car car = new Car();

        Ticket ticket = graduateParkingBoy.park(car);

        assertNotNull(ticket);
        assertEquals(car, parkingLot2.fetch(ticket));
        assertThrows(InvalidTicketException.class, () -> parkingLot1.fetch(ticket));
    }

    @Test
    void should_return_a_ticket_and_park_car_at_first_parking_lot_when_park_given_first_parking_lot_is_available_and_second_is_full() throws Exception {
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        parkingLot2.park(new Car());
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLot1, parkingLot2);
        Car car = new Car();

        Ticket ticket = graduateParkingBoy.park(car);

        assertNotNull(ticket);
        assertEquals(car, parkingLot1.fetch(ticket));
        assertThrows(InvalidTicketException.class, () -> parkingLot2.fetch(ticket));
    }

    @Test
    void should_throw_parking_lot_is_full_exception_when_park_given_two_full_parking_lots() throws Exception {
        ParkingLot parkingLot1 = new ParkingLot(1);
        parkingLot1.park(new Car());
        ParkingLot parkingLot2 = new ParkingLot(1);
        parkingLot2.park(new Car());
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLot1, parkingLot2);

        assertThrows(ParkingLotIsFullException.class, () -> graduateParkingBoy.park(new Car()));
    }

    @Test
    void should_fetch_the_car_when_fetch_given_two_parking_lots_and_the_car_is_parked_at_the_first_and_the_ticket() throws Exception {
        ParkingLot parkingLot1 = new ParkingLot(1);
        Car car = new Car();
        Ticket ticket = parkingLot1.park(car);
        ParkingLot parkingLot2 = new ParkingLot(1);
        parkingLot2.park(new Car());
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLot1, parkingLot2);

        Car fetchedCar = graduateParkingBoy.fetch(ticket);

        assertEquals(car, fetchedCar);
    }

    @Test
    void should_fetch_the_car_when_fetch_given_two_parking_lots_and_the_car_is_parked_at_the_second_and_the_ticket() throws Exception {
        ParkingLot parkingLot1 = new ParkingLot(1);
        parkingLot1.park(new Car());
        ParkingLot parkingLot2 = new ParkingLot(1);
        Car car = new Car();
        Ticket ticket = parkingLot2.park(car);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLot1, parkingLot2);

        Car fetchedCar = graduateParkingBoy.fetch(ticket);

        assertEquals(car, fetchedCar);
    }

    @Test
    void should_throw_invalid_ticket_exception_when_fetch_given_two_parking_lots_and_invalid_ticket() throws Exception {
        ParkingLot parkingLot1 = new ParkingLot(1);
        parkingLot1.park(new Car());
        ParkingLot parkingLot2 = new ParkingLot(1);
        parkingLot2.park(new Car());
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLot1, parkingLot2);

        assertThrows(InvalidTicketException.class, () -> graduateParkingBoy.fetch(new Ticket()));
    }

    @Test
    void should_throw_invalid_ticket_exception_when_fetch_given_two_parking_lots_without_my_car_and_used_ticket() throws Exception {
        ParkingLot parkingLot1 = new ParkingLot(1);
        Ticket ticket = parkingLot1.park(new Car());
        parkingLot1.fetch(ticket);
        ParkingLot parkingLot2 = new ParkingLot(1);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLot1, parkingLot2);

        assertThrows(InvalidTicketException.class,  () -> graduateParkingBoy.fetch(ticket));
    }
}
