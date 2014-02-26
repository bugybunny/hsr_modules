/*
 * HSR - Uebungen Programmieren 2: Algorithmen & Datenstrukturen
 * $LastChangedDate: 2013-02-20 16:50:16 +0100 (Mi, 20 Feb 2013) $
 */

package ch.hsr.prog2.exercises.week2.aufgabe9;

public class MatrixImpl implements Matrix {

  // 2D-array float
  private double[][] data = null;

  // 32bit scalars
  private int size_x;

  private int size_y;

  public MatrixImpl(int iSizeX, int iSizeY) {
    size_x = iSizeX;
    size_y = iSizeY;
    data = new double[size_x][size_y];
  }

  public void transpose() {
    double[][] new_data = new double[size_y][size_x];
    for (int i = 0; i < size_x; i++) {
      for (int j = 0; j < size_y; j++) {
        new_data[j][i] = data[i][j];
      }
    }
    int dummy = size_x;
    size_x = size_y;
    size_y = dummy;
    data = new_data;
  }

  public Matrix mult(Matrix right) {
    if (right.get_sizey() != get_sizex())
      return null;
    Matrix result = new MatrixImpl(right.get_sizex(), get_sizey());
    for (int i = 0; i < right.get_sizex(); i++) {
      for (int j = 0; j < get_sizey(); j++) {
        double sum = 0;
        for (int k = 0; k < get_sizex(); k++) {
          sum += right.get(i, k) * get(k, j);
        }
        result.set(i, j, sum);
      }
    }
    return result;
  }

  public int get_sizex() {
    return size_x;
  }

  public int get_sizey() {
    return size_y;
  }

  public double get(int x, int y) {
    return data[x][y];
  }

  public void set(int x, int y, double val) {
    if ((x < 0) || (y < 0) || (x >= size_x) || (y >= size_y))
      return;
    data[x][y] = val;
  }

  public Matrix copy() {
    Matrix result = new MatrixImpl(get_sizex(), get_sizey());
    for (int i = 0; i < get_sizex(); i++) {
      for (int j = 0; j < get_sizey(); j++) {
        result.set(i, j, get(i, j));
      }
    }
    return result;
  }

  public void print() {
    for (int i = 0; i < get_sizey(); i++) {
      System.out.print("[ ");
      for (int j = 0; j < get_sizex(); j++) {
        System.out.print(get(j, i) + " ");
      }
      System.out.println("]");
    }
  }
}
 
 
