/*
 * HSR - Uebungen Programmieren 2: Algorithmen & Datenstrukturen
 * $LastChangedDate: 2013-02-20 16:50:16 +0100 (Mi, 20 Feb 2013) $
 */

package ch.hsr.prog2.exercises.week2.aufgabe8;

public abstract class AbstractBikeStore {

    public abstract void addBike(Bike b);

    public abstract Bike removeBike(int i);

    public abstract void clear();

    public abstract int size();
}
