package ch.hsr.prog2.pruefungen._2012.aufgabe1_7;

/**
 * TODO COMMENT ME!
 * 
 * @author msyfrig
 */
public class HeightWidthTreeMethod {

    public static Object[] tree = new Object[40];

    static {
        // for (int i = 0; i < tree.length; i++) {
        //
        // }
        tree[1] = Character.valueOf('-');
        tree[2] = Character.valueOf('+');
        tree[3] = Character.valueOf('/');
        tree[4] = Integer.valueOf(23);
        tree[5] = Character.valueOf('*');
        tree[6] = Integer.valueOf(4);
        tree[7] = Integer.valueOf(8);
        tree[10] = Character.valueOf('*');
        tree[11] = 21;
        tree[20] = 4;
        tree[21] = 8;
    }

    public static void main(String[] args) {
        int lastElement = tree.length;
        while (tree[--lastElement] == null) {
        }
        int height = 0;

        System.out.println(lastElement);
        while (lastElement > 1) {
            lastElement /= 2;
            height++;
        }
        System.out.println(height);
        System.out.println(Math.pow(2, height));
    }
}
