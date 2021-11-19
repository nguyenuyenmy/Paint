package paint;

import java.io.Serializable;

public class Point implements Serializable {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Allows to retrieve X.
     * @return
     */
    public int getX() {
        return this.x;
    }

    /**
     * Allows to retrieve Y.
     * @return
     */
    public int getY() {
        return this.y;
    }

    /**
     * Allows to modify x.
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Allows to modify y.
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}