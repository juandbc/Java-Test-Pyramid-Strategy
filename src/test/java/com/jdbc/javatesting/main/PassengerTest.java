package com.jdbc.javatesting.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.jdbc.javatesting.main.Passenger;
import org.junit.jupiter.api.Test;

class PassengerTest {

  @Test
  public void testPassengerCreation() {
    Passenger passenger = new Passenger("123-45-6789", "Fulanito", "CO");
    assertNotNull(passenger, "Test Failed, the Object passenger is null");
  }

  @Test
  public void testInvalidSsn() {
    assertThrows(RuntimeException.class, () -> {
      new Passenger("123-456-78", "Fulanito", "CO");
    });
  }

  @Test
  public void testInvalidCountryCode() {
    assertThrows(RuntimeException.class, () -> {
      new Passenger("123-456-789", "Fulanito", "GJ");
    });
  }

  @Test
  public void testSetInvalidSSn() {
    assertThrows(RuntimeException.class, () -> {
      Passenger passenger = new Passenger("123-456-789", "Fulanito", "CO");
      passenger.setIdentifier(".");
    });
  }

  @Test
  public void testSetValidSSn() {
    String id = "566-65-3124";
    Passenger passenger = new Passenger("123-45-1789", "Fulanito", "CO");
    passenger.setIdentifier(id);
    assertEquals(id, passenger.getIdentifier(), "I");
  }

  @Test
  public void testSetInvalidCountryCode() {
    assertThrows(RuntimeException.class, () -> {
      Passenger passenger = new Passenger("123-45-789", "Fulanito", "CO");
      passenger.setCountryCode("UK");
    });
  }

  @Test
  public void testSetValidCountryCode() {
    String cc = "VE";
    Passenger passenger = new Passenger("123-33-7890", "Fulanito", "CO");
    passenger.setCountryCode(cc);
    assertEquals(cc, passenger.getCountryCode());
  }

  @Test
  public void testToString() {
    Passenger passenger = new Passenger("123-45-7089", "Fulanito", "CO");
    passenger.setName("Juanito");
    passenger.recordToSystem();
    assertEquals("Passenger Juanito with identifier: 123-45-7089 from CO", passenger.toString());
  }
}