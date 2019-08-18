package com.jdbc.javatesting.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class FlightTest {

  @Test
  public void testFlightCreation() {
    Flight flight = new Flight("A320", 2);
    assertNotNull(flight, "Test Failed, the Object flight is null");
  }

  @Test
  public void testInvalidFlightNumber() {
    assertThrows(RuntimeException.class, () -> {
      new Flight("BB52", 2);
    });
    assertThrows(RuntimeException.class, () -> {
      new Flight("AA12045", 100);
    });
  }

  @Test
  public void testValidFlightNumber() {
    Flight flight = new Flight("B852", 2);
    assertEquals("B852", flight.getFlightNumber());

    flight = new Flight("AA312", 10);

    assertEquals("AA312", flight.getFlightNumber());    
  }

  @Test
  public void testSellTickets() throws IOException {
    Flight flight = FlightBuilderUtil.buildFlightFromCsv();
    
    assertEquals(50, flight.getPlaces());
    assertThrows(RuntimeException.class, () -> {
      flight.addPassenger(new Passenger("999-12-0102", "Fulanita", "CO"));
    });
  }

  @Test
  public void testAddOrRemovePassenger() throws IOException {
    Flight flight = FlightBuilderUtil.buildFlightFromCsv();
    flight.setPlaces(100);
    
    assertEquals(50, flight.getPassengersNumber());

    Passenger additionalPassenger = new Passenger("953-54-9787", "Ana", "CO");
    flight.addPassenger(additionalPassenger);

    assertEquals(51, flight.getPassengersNumber());

    flight.removePassenger(additionalPassenger);

    assertEquals(50, flight.getPassengersNumber());
  }

  @Test
  public void testSetInvalidPlaces() throws IOException {
    Flight flight = FlightBuilderUtil.buildFlightFromCsv();
    
    assertThrows(RuntimeException.class, () -> {
      flight.setPlaces(10);
    });
  }

  @Test
  public void testSetValidPlaces() throws IOException {
    Flight flight = FlightBuilderUtil.buildFlightFromCsv();

    assertEquals(50, flight.getPlaces());

    flight.setPlaces(70);

    assertEquals(70, flight.getPlaces());
  }

  @Test
  public void testChangeOrigin() {
    Flight flight = new Flight("A1890", 20);
    flight.setOrigin("London");
    flight.setDestination("Barranquilla");
    flight.takeOff();

    assertEquals(true, flight.isFlying());
    assertThrows(RuntimeException.class, () -> {
      flight.setOrigin("Medellín");
    });
  }

  @Test
  public void testTakeOff() {
    Flight flight = new Flight("A1890", 2);
    flight.setOrigin("London");
    flight.setDestination("Barranquilla");
    flight.takeOff();

    assertThrows(RuntimeException.class, () -> {
      flight.takeOff();
    });
  }

  @Test
  public void testLand() {
    Flight flight = new Flight("A1890", 2);
    flight.setOrigin("London");
    flight.setDestination("Barranquilla");
    flight.takeOff();

    assertEquals(true, flight.isTakenOff());
    assertEquals(false, flight.isLanded());
    
    flight.land();

    assertThrows(RuntimeException.class, () -> {
      flight.land();
    });
    assertEquals(true, flight.isTakenOff());
    assertEquals(true, flight.isLanded());
    assertEquals(false, flight.isFlying());
  }

  @Test
  public void testChangeDestination() {
    Flight flight = new Flight("A1890", 20);
    flight.setOrigin("London");
    flight.setDestination("Barranquilla");
    flight.takeOff();
    flight.land();

    assertThrows(RuntimeException.class, () -> {
      flight.setDestination("Medellín");
    });
  }

  @Test
  public void testToString() {
    Flight flight = new Flight("A1890", 2);
    flight.setOrigin("London");
    flight.setDestination("Barranquilla");
    assertEquals("Flight A1890 from London to Barranquilla", flight.toString());
  }
}