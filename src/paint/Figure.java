package paint;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public abstract class Figure implements Serializable {
    protected Color c; //The color of our figure
    protected Point origin; //The origin point of our figure
    protected boolean isFilled; //Determines whether the rectangle, ellipse, square or circle figures are going to be filled
    protected int strokeSize; //Determines the size of our figures' borders

    public Figure(Color c, Point p, boolean isFilled, int strokeSize) {
        this.c = c;
        this.origin = p;
        this.isFilled = isFilled;
        this.strokeSize = strokeSize;
    }

    public abstract void draw(Graphics g);
    public abstract void setBoundingBox(int heightBB, int widthBB);

    /**
     * Allows to retrieve the origin point of our figure.
     * @return
     */
    public Point getOrigin() {
        return origin;
    }

    /**
     * Allows to retrieve the color.
      * @return
     */
    public Color getC() {
        return c;
    }

    /**
     * Allows to get the minimum between height and width of our figures.
     * @param heightBB
     * @param widthBB
     * @return
     */
    protected int getMinAbs(int heightBB, int widthBB) {
        int height = Math.abs(heightBB);
        int width = Math.abs(widthBB);
        if (height > width) {
            height = width;
        }
        return height;
    }

    /**
     * Allows us to retrieve the value with the good sign
     * @param oldValue
     * @param value
     * @return
     */
    protected int getSignedValue(int oldValue, int value) {
        int signedValue = value;
        if (oldValue < 0) {
            signedValue = -signedValue;
        }
        return signedValue;
    }

    @Override
    public String toString() {
        return "Color is " + c;
    }
}

