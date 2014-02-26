/*
 * HSR - Uebungen Programmieren 2: Algorithmen & Datenstrukturen
 * $LastChangedDate: 2013-02-20 16:50:16 +0100 (Mi, 20 Feb 2013) $
 */

package ch.hsr.prog2.exercises.week2.aufgabe9;

public interface Vektor {

  /**
   * Returns the number of elements in this vector.
   * @return The Number of elements.
   */
  int get_size();

  double get(int x);

  void set(int x, double val);

  /**
   * Calculates the scalar product of this vector multiplied with the 'right'-vector.
   * @param right The vector to multiply with.
   * @return  The scalar product of this vektor multiplied with 'right'.
   */
  double mult(Vektor right);

  /**
   * Multiplies the matrix 'left' with this vector as column vector.
   * @param left The matrix to multiply with.
   * @return The resulting column vector.
   */
  Vektor mult(Matrix left);

  /**
   * Transposes this vector: (V^T)ij = Vji
   * This means implicite, that a column vector becomes a row vector and vice versa.
   */
  void transpose();

  /**
   * Creates from this vector a new matrix with the same content. 
   * @return The new created matrix from this vector.
   */
  Matrix as_matrix();

  /**
   * Creates a identical copy of this vector.
   * @return The new created copy of this vector.
   */
  Vektor copy();

  void print();
}
 
