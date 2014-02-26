/*
 * HSR - Uebungen Programmieren 2: Algorithmen & Datenstrukturen
 * $LastChangedDate: 2013-03-14 15:56:23 +0100 (Do, 14 Mrz 2013) $
 */

package ch.hsr.prog2.exercises.week5.aufgabe1;

public class Walker {

    enum Direction {

        NORTH(0, -1), WEST(-1, 0), EAST(1, 0), SOUTH(0, 1);

        private int offsetX;
        private int offsetY;

        Direction(int offsetX, int offsetY) {
            this.offsetX = offsetX;
            this.offsetY = offsetY;
        }

        int getOffsetX() {
            return offsetX;
        }

        int getOffsetY() {
            return offsetY;
        }

    }

    /**
     * The Labyrinth where the Walker is.
     */
    private Labyrinth lab;

    public Walker() {
        lab = new Labyrinth();
    }

    /**
     * Recursive method, which is called for every position to check.
     * 
     * @param x
     *            X-Position to check.
     * @param y
     *            Y-Position to check.
     * @return True if the destination is found, else false.
     */
    public boolean walk(int x, int y) {
        lab.setField(x, y, PositionState.CURRENT_POSITION);
        if (x == lab.getEndCol() && y == lab.getEndRow()) {
            return true;
        }
        lab.setField(x, y, PositionState.WALKED);
        printLabyrinth();

        Point p = new Point(x, y);
        Point openDirection = null;

        for (Direction tempDirection : Direction.values()) {
            Point newFront = rotateAndGetPosition(p, tempDirection);
            PositionState newFrontState = lab.getField(newFront.x, newFront.y);
            switch (newFrontState) {
                case OPEN:
                    return walk(newFront.x, newFront.y);
                case WALKED:
                    openDirection = newFront;
                    break;
                default:
                    break; // next direction should be checked
            }
        }
        lab.setField(p.x, p.y, PositionState.BACKTRACKED);
        if (openDirection != null) {
            return walk(openDirection.x, openDirection.y);
        } else { // is null when no more possibilities and the end doesn’t exist
            return false;
        }
    }

    private final Point rotateAndGetPosition(Point aCurrentPos,
            Direction aDirection) {
        Point p = new Point(aCurrentPos.x + aDirection.getOffsetX(),
                aCurrentPos.y + aDirection.getOffsetY());
        return p;
    }

    private void printLabyrinth() {
        System.out
                .print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out
                .print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        lab.print();
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        }
        catch (InterruptedException ex) {
        }
    }

    public static void main(String[] args) {
        Walker walker = new Walker();
        // start walking at the start position
        if (walker.walk(walker.lab.getStartCol(), walker.lab.getStartRow())) {
            System.out.println("Finally found a way out!");
        } else {
            System.out.println("Desperate search ended unsuccessful...");
        }

    }
}

class Point {
    int x, y;

    protected Point(int aX, int aY) {
        x = aX;
        y = aY;
    }
}

/*
 * Session-Log (SOLL):
 * 
 * # # # # # # # # # # # # # # o # . . # . . . . . . # # o # . # # . # . # . # #
 * # o o . # . . # . # . . # # # o # # # # # . # # # # # . o o o o o o o o o . #
 * # . # # # o o # # # o # # # . . . # o o o o o o # # # # # # o # # # # # # # .
 * . . . o # . # # # # . # # # o # . # X # # # # . # . # o o o # o # # # . . . #
 * # # o o o # # # # # # # # # # # # # # Evaluating new possible solution (9/10)
 * Finally found a way out!
 */

