/*
 * HSR - Uebungen Programmieren 2: Algorithmen & Datenstrukturen
 * Version: Thu Mar 21 16:29:41 CET 2013
 */

package ch.hsr.prog2.exercises.week6.aufgabe2;

public class Airplane {  

  private String departureAirport;
  private long quantityOfPetrol;

  public Airplane(String departureAirport, long quantityOfPetrol) {
    this.departureAirport = departureAirport;
    this.quantityOfPetrol = quantityOfPetrol;
  }

  public String getDepartureAirport() {
    return departureAirport;
  }

  public long getQuantityOfPetrol() {
    return quantityOfPetrol;
  }

}
 
 
 
 
