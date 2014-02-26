package week3.exercises;

import java.awt.Point;

/**
 * @author Marco
 */
public class Rectangle {
    private Point topLeft;
    private Point bottomRight;

    /**
     * Creates a new instance of this class.
     * 
     * @param aTopLeftPoint
     * @param aBottomRight
     */
    public Rectangle(Point aTopLeftPoint, Point aBottomRight) {
        super();
        topLeft = aTopLeftPoint;
        bottomRight = aBottomRight;
    }

    public Rectangle(Point aTopLeftPoint, int aSize) {
        this(aTopLeftPoint, new Point(aTopLeftPoint.x + aSize, aTopLeftPoint.y));
    }

    /**
     * @return the topLeft
     */
    public Point getTopLeft() {
        return topLeft;
    }

    /**
     * @return the bottomRight
     */
    public Point getBottomRight() {
        return bottomRight;
    }

    public boolean isSquare() {
        int vertical = topLeft.y - bottomRight.y;
        int horizontal = topLeft.x - bottomRight.x;

        return vertical == horizontal;
    }

    public boolean isSame(Rectangle anOtherRectangle) {
        return equals(anOtherRectangle);
    }

    public boolean encloses(Rectangle anOtherRectangle) {
        boolean enclosing = false;

        if (equals(anOtherRectangle)) {
            return true;
        }
        return enclosing;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((bottomRight == null) ? 0 : bottomRight.hashCode());
        result = prime * result + ((topLeft == null) ? 0 : topLeft.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Rectangle other = (Rectangle) obj;
        if (bottomRight == null) {
            if (other.bottomRight != null) {
                return false;
            }
        }
        else if (!bottomRight.equals(other.bottomRight)) {
            return false;
        }
        if (topLeft == null) {
            if (other.topLeft != null) {
                return false;
            }
        }
        else if (!topLeft.equals(other.topLeft)) {
            return false;
        }
        return true;
    }

}
