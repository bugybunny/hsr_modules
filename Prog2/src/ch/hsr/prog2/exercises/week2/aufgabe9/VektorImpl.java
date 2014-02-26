/*
 * HSR - Uebungen Programmieren 2: Algorithmen & Datenstrukturen
 * $LastChangedDate: 2013-02-20 16:50:16 +0100 (Mi, 20 Feb 2013) $
 */

package ch.hsr.prog2.exercises.week2.aufgabe9;

public class VektorImpl implements Vektor {
    private MatrixImpl matrix;

    public VektorImpl(int i) {
        matrix = new MatrixImpl(1, i);
    }

    @Override
    public int get_size() {
        return matrix.get_sizex() * matrix.get_sizey();
    }

    @Override
    public double get(int x) {
        return matrix.get(1, x);
    }

    @Override
    public void set(int x, double val) {
        matrix.set(1, x, val);
    }

    @Override
    public double mult(Vektor right) {
        Matrix resultMatrix = matrix.mult(right.as_matrix());

        int size = resultMatrix.get_sizex() > resultMatrix.get_sizey() ? resultMatrix
                .get_sizex() : resultMatrix.get_sizey();
        for (int i = 0; i < size; i++) {

        }

        return 0;
    }

    @Override
    public Vektor mult(Matrix left) {
        // TODO Implement here...
        return null;
    }

    @Override
    public void transpose() {
        matrix.transpose();
    }

    @Override
    public Matrix as_matrix() {
        Matrix vectorAsMatrix = new MatrixImpl(1, get_size());
        return null;
    }

    @Override
    public Vektor copy() {
        // TODO Implement here...
        return null;
    }

    @Override
    public void print() {
        matrix.print();
    }
}
