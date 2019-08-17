package com.jdbc.javatesting.main;

public class App {

  /**
   * .
   * @param args arguments
   */
  public static void main(String[] args) {
    Passenger passenger = new Passenger("123-45-6789", "John Smith", "GB");
    passenger.setIdentifier("362-02-6342");
    passenger.recordToSystem();

    Flight flight = new Flight("BA884", 100);
    flight.setOrigin("London");
    flight.setDestination("Bucharest");
    flight.sellTicket();
    flight.takeOff();
    flight.land();
  }

  public String getGreeting() {
    return "Hello World!";
  }
}
