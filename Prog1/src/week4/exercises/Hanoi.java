package week4.exercises;

public class Hanoi {
    private static final int NOF_DISKS = 10;
    private HanoiGUI         gui;

    public Hanoi() {
        gui = new HanoiGUI(NOF_DISKS);
        solveHanoi(NOF_DISKS, 0, 1, 2); // move all disks from pile 0 to pile 1
                                        // by using the reserve pile 2
    }

    /**
     * Move an specified amount of disks from a source pile to a target pile.
     * 
     * @param nofDisks
     *            the amount of disks to be moved (0 means none)
     * @param sourcePileNo
     *            the number of the source pile (0..2)
     * @param targetPileNo
     *            the number of the target pile (0..2)
     * @param reservePileNo
     *            the number of the reserve pile (0..2)
     */
    private void solveHanoi(int nofDisks, int sourcePileNo, int targetPileNo,
            int reservePileNo) {
        // TODO: Your implementation...
        // Call the method move() for each disk move.
    }

    private void move(int diskNo, int fromPileNo, int toPileNo) {
        System.out.println(" MOVE " + diskNo + " FROM " + fromPileNo + " TO "
                + toPileNo);
        gui.move(diskNo, fromPileNo, toPileNo);
    }

    public static void main(String[] args) {
        new Hanoi();
    }
}
