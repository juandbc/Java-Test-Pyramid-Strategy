package com.jdbc.javatesting.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.jdbc.javatesting.main.Passenger;
import org.junit.jupiter.api.Test;

class PassengerTest {

  @Test
  public void testPassengerCreation() {
    Passenger passenger = new Passenger("123-45-6789", "Fulanito", "US");
    assertNotNull(passenger, "Test Failed, the Object passenger is null");
  }

  @Test
  public void testInvalidSsn() {
    assertThrows(RuntimeException.class, () -> {
      new Passenger("123-456-78", "Fulanito", "US");
    });
    assertThrows(RuntimeException.class, () -> {
      new Passenger("957-456-7800", "Fulanito", "US");
    });
  }

  @Test
  public void testInvalidCountryCode() {
    assertThrows(RuntimeException.class, () -> {
      new Passenger("900-456-789", "Fulanito", "VH");
    });
  }

  @Test
  public void testSetInvalidSSn() {
    assertThrows(RuntimeException.class, () -> {
      Passenger passenger = new Passenger("123-456-789", "Fulanito", "VH");
      passenger.setIdentifier(".");
    });
  }

  @Test
  public void testSetValidSSn() {
    String id = "566-65-3124";
    Passenger passenger = new Passenger("123-45-1789", "Fulanito", "US");
    passenger.setIdentifier(id);
    assertEquals(id, passenger.getIdentifier(), "I");
  }

  @Test
  public void testSetInvalidCountryCode() {
    assertThrows(RuntimeException.class, () -> {
      Passenger passenger = new Passenger("123-45-789", "Fulanito", "US");
      passenger.setCountryCode("UK");
    });
  }

  @Test
  public void testSetValidCountryCode() {
    String cc = "VE";
    Passenger passenger = new Passenger("123-33-7890", "Fulanito", "US");
    passenger.setCountryCode(cc);
    assertEquals(cc, passenger.getCountryCode());
  }

  @Test
  public void testToString() {
    Passenger passenger = new Passenger("123-45-7089", "Fulanito", "US");
    passenger.setName("Juanito");
    passenger.recordToSystem();
    assertEquals("Passenger Juanito with identifier: 123-45-7089 from US", passenger.toString());
  }

  @Test
  public void testValidSsnNoUsPassenger() {
    assertThrows(RuntimeException.class, () -> {
      new Passenger("999-456-78", "Fulanito", "CO");
    });
  }
}