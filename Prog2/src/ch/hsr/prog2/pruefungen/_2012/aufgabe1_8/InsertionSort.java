package ch.hsr.prog2.pruefungen._2012.aufgabe1_8;

public class InsertionSort {

    public static int compare(int[] o1, int[] o2) {
        if (o1.length != 3 || o2.length != 3) {
            System.err.println("Parameter ist nicht i[3]");
            System.exit(9);
        }
        // 2nd column
        int result = comp(o1[1], o2[1]);
        if (result == 0) {
            int secondresult = comp(o1[0], o2[0]);
            if (secondresult == 0) {
                return comp(o1[2], o2[2]);

            } else {
                return secondresult;
            }
        } else {
            return result;
        }
    }

    private static int comp(int i, int j) {
        return Integer.compare(i, j);
    }

    public static void main(String[] args) {

    }
}
