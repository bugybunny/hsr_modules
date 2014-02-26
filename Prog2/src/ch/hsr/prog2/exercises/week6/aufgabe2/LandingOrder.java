/*
 * HSR - Uebungen Programmieren 2: Algorithmen & Datenstrukturen
 * Version: Thu Mar 21 16:29:41 CET 2013
 */

package ch.hsr.prog2.exercises.week6.aufgabe2;

public class LandingOrder {

  private PriorityQueue<Long, Airplane> pq;

  public LandingOrder() {
    // TODO Implement here...
  }

  public void addAirplane(Airplane airplane) {
    // TODO Implement here...
  }

  public Airplane nextAirplane() {
    // TODO Implement here...
    return null;
  }

  public boolean isEmpty() {
    // TODO Implement here...
    return false;
  }

  public static void main(String[] args) {
    LandingOrder landingOrder = new LandingOrder();

    landingOrder.addAirplane(new Airplane("Basel", 20));
    landingOrder.addAirplane(new Airplane("Geneva", 100));
    landingOrder.addAirplane(new Airplane("New-York", 10));
    landingOrder.addAirplane(new Airplane("London", 5));
    landingOrder.addAirplane(new Airplane("Tel Aviv", 300));

    Airplane nextLanding;
    while ((nextLanding = landingOrder.nextAirplane()) != null) {
      System.out.println("Airplane from " + nextLanding.getDepartureAirport()
          + " has landed");
    }

    System.out.println("All airplanes have landed.");
  }
}

/* Session-Log (SOLL):

Airplane from London has landed
Airplane from New-York has landed
Airplane from Basel has landed
Airplane from Geneva has landed
Airplane from Tel Aviv has landed
All airplanes have landed.

*/

 
 
 
 
