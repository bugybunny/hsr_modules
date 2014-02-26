package ch.hsr.prog2.exercises.week3;

public class HanoiAlgorithmRecursion {
    public static void movetower(int height, int fromT, int toT, int usingT) {
        if (height > 0) {
            movetower(height - 1, fromT, usingT, toT);
            moveDisk(fromT, toT);
            movetower(height - 1, usingT, toT, fromT);
        }
    }

    public static void moveDisk(int takeoff, int puton) {
        System.out.println(takeoff + "->" + puton);
    }

    public static void main(String argv[]) {
        int numberOfDisks = 3;
        movetower(numberOfDisks, 1, 3, 2);
    }
}