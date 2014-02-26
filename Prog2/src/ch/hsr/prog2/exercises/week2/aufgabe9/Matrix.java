/*
 * HSR - Uebungen Programmieren 2: Algorithmen & Datenstrukturen
 * $LastChangedDate: 2013-02-20 16:50:16 +0100 (Mi, 20 Feb 2013) $
 */

package ch.hsr.prog2.exercises.week2.aufgabe9;

public interface Matrix {

  int get_sizex();

  int get_sizey();

  double get(int x, int y);

  void set(int x, int y, double val);

  void transpose();

  Matrix mult(Matrix right);

  Matrix copy();

  void print();
}
 
 
