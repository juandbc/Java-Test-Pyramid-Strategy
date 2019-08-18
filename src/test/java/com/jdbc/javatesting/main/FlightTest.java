package com.jdbc.javatesting.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.jdbc.javatesting.main.Flight;
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
  public void testSellTickets() {
    Flight flight = new Flight("A1890", 50);
    flight.setOrigin("London");
    flight.setDestination("Barranquilla");

    for (int i = 1; i <= flight.getPlaces(); i++) {
      flight.sellTicket();
    }
    assertEquals(50, flight.getPlaces());
    assertThrows(RuntimeException.class, () -> {
      flight.sellTicket();
    });
  }

  @Test
  public void testSetInvalidPlaces() {
    Flight flight = new Flight("A1890", 20);
    flight.setOrigin("London");
    flight.setDestination("Barranquilla");
    for (int i = 1; i <= flight.getPlaces(); i++) {
      flight.sellTicket();
    }
    assertEquals(20, flight.getPlaces());
    assertThrows(RuntimeException.class, () -> {
      flight.setPlaces(10);
    });
  }

  @Test
  public void testSetValidPlaces() {
    Flight flight = new Flight("A1890", 20);
    flight.setOrigin("London");
    flight.setDestination("Barranquilla");
    for (int i = 1; i <= flight.getPlaces(); i++) {
      flight.sellTicket();
    }
    assertEquals(20, flight.getPlaces());

    flight.setPlaces(30);

    assertEquals(30, flight.getPlaces());
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
  public void testLand() {
    Flight flight = new Flight("A1890", 2);
    flight.setOrigin("London");
    flight.setDestination("Barranquilla");
    flight.takeOff();

    assertEquals(true, flight.isTakenOff());
    assertEquals(false, flight.isLanded());
    
    flight.land();

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