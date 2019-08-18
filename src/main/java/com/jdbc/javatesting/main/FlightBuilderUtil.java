package com.jdbc.javatesting.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * FlightBuilderUtil.
 */
public class FlightBuilderUtil {

  /**
   * Create a flight with a list of passengers from a CSV file.
   */
  public static Flight buildFlightFromCsv() throws IOException {
    Flight flight = new Flight("A320", 50);
    flight.setOrigin("Barranquilla");
    flight.setDestination("Medellín");

    try (BufferedReader bf = Files.newBufferedReader(Paths.get("src", "test", "resources", 
        "flights_information.csv"))) {
      String line;
      String[] fields;

      while ((line = bf.readLine()) != null) {
        fields = line.split(";");

        Passenger passenger = new Passenger(fields[0], fields[1].trim(), fields[2].trim());
        flight.addPassenger(passenger);
      }

      return flight;
    }
  }
}