package com.jdbc.javatesting.main;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Flight {

  private String flightNumber;
  private int places;
  private Set<Passenger> passengers;
  private String origin;
  private String destination;
  private boolean flying;
  private boolean takenOff;
  private boolean landed;

  private String flightNumberRegex = "^[A-Z]{1,2}\\d{3,4}$";
  private Pattern pattern = Pattern.compile(flightNumberRegex);

  /**
   * Crea un nuevo vuelo.
   * @param flightNumber número de vuelo
   * @param places número de lugares
   */
  public Flight(String flightNumber, int places) {
    Matcher matcher = pattern.matcher(flightNumber);
    
    if (!matcher.matches()) {
      throw new RuntimeException("Invalid flight number");
    }

    this.flightNumber = flightNumber;
    this.places = places;
    this.passengers = new HashSet<>();
    this.flying = false;
    this.takenOff = false;
    this.landed = false;
  }

  public String getFlightNumber() {
    return flightNumber;
  }

  public int getPlaces() {
    return places;
  }

  public void setPlaces(int places) {
    if (passengers.size() > places) {
      throw new RuntimeException("Cannot reduce the number of places under the number of existing" 
          + " passengers!");
    }
    this.places = places;
  }

  public void addPassenger(Passenger passenger) {
    sellTicket(passenger);
    this.passengers.add(passenger);
  }

  public void removePassenger(Passenger passenger) {
    this.passengers.remove(passenger);
  }

  public int getPassengersNumber() {
    return passengers.size();
  }

  public String getOrigin() {
    return origin;
  }

  public void setOrigin(String origin) {
    if (takenOff) {
      throw new RuntimeException("Flight cannot change its origin any longer");
    }
    this.origin = origin;
  }

  public String getDestination() {
    return destination;
  }

  public void setDestination(String destination) {
    if (landed) {
      throw new RuntimeException("Flight cannot change its destination any longer");
    }
    this.destination = destination;
  }

  public boolean isFlying() {
    return flying;
  }

  public boolean isTakenOff() {
    return takenOff;
  }

  public boolean isLanded() {
    return landed;
  }

  @Override
  public String toString() {
    return "Flight " + getFlightNumber() + " from " + getOrigin() + " to " + getDestination();
  }

  private void sellTicket(Passenger passenger) {
    if (passengers.size() >= places) {
      throw new RuntimeException("Sorry, not enough places");
    }
    System.out.println("Ticket for flight " + flightNumber + " sold to " + passenger.getName());
  }

  public void takeOff() {
    if (takenOff) {
      throw new RuntimeException("Cannot take off, the flight already taken off");
    }
    System.out.println(this + " is taking off");
    flying = true;
    takenOff = true;
  }

  public void land() {
    if (landed) {
      throw new RuntimeException("Cannot land while on earth");
    }
    System.out.println(this + " is landing");
    flying = false;
    landed = true;
  }
}