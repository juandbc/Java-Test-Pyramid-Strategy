package com.jdbc.javatesting.main;

import java.lang.RuntimeException;
import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Passenger {

  private String identifier;
  private String name;
  private String countryCode;
  private String ssnRegex = "^(?!000|666)[0-8][0-9]{2}-(?!00)[0-9]{2}-(?!0000)[0-9]{4}$";
  private String noUsIdentifierRegex = "^(?!000|666)[9][0-9]{2}-(?!00)[0-9]{2}-(?!0000)[0-9]{4}$";
  private Pattern pattern;

  /**
   * Crea un nuevo pasajero.
   * 
   * @param identifier  número de pasajero, usa el estandar
   *                    SSNs: 9 digit numbers, AAA-GG-SSSS.
   *                    First three digits cannot be 000,666, or between 900 and
   *                    999. Digits 4 and 5: group number, from 01 to 99. Last 4
   *                    digits: serial numbers from 0001 to 9999
   * @param name        nombre del pasajero
   * @param countryCode código del país del pasajero
   */
  public Passenger(String identifier, String name, String countryCode) {
    pattern = countryCode.equals("US") ? Pattern.compile(ssnRegex) : 
        Pattern.compile(noUsIdentifierRegex);

    Matcher matcher = pattern.matcher(identifier);

    if (!matcher.matches()) {
      throw new RuntimeException("Invalid identifier");
    }

    if (!Arrays.asList(Locale.getISOCountries()).contains(countryCode)) {
      throw new RuntimeException("Invalid country code");
    }

    this.identifier = identifier;
    this.name = name;
    this.countryCode = countryCode;
  }

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    Matcher matcher = pattern.matcher(identifier);

    if (!matcher.matches()) {
      throw new RuntimeException("Invalid identifier");
    }
    this.identifier = identifier;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    if (!Arrays.asList(Locale.getISOCountries()).contains(countryCode)) {
      throw new RuntimeException("Invalid country code");
    }
    this.countryCode = countryCode;
  }

  @Override
  public String toString() {
    return "Passenger " + getName() + " with identifier: " + getIdentifier() + " from " 
      + getCountryCode();
  }

  public void recordToSystem() {
    System.out.println(this + " has been recorded to the company system");
  }
}
