package paint;

import java.awt.*;

public class Rectangle extends Figure {
    protected int length;
    protected int width;

    public Rectangle(int px, int py, Color color, boolean isFilled, int strokeSize) {
        super(color, new Point(px, py), isFilled, strokeSize);
        length = 0;
        width = 0;
    }

    /**
     * Allows to get the length of our rectangle
     * @return
     */
    public int getLength() {
        return length;
    }

    /**
     * Allows to get the width of our rectangle
     * @return
     */
    public int getWidth(){
        return width;
    }

    /**
     * Allows to get the perimeter of our rectangle
     * @return
     */
    public int getPerimeter() {
        return 2*length+2*width;
    }

    /**
     * Allows to get the surface of our rectangle
     * @return
     */
    public int getSurface() {
        return length*width;
    }

    /**
     * Allows to set the length.
     * @return
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * Allows to set the width.
     * @return
     */
    public void setWidth(int width) {
        this.width = width;
    }


    @Override
    public void draw(Graphics g) {
        int px = this.origin.getX();
        int py = this.origin.getY();
        g.setColor(this.getC());

        //If we drag our mouse to the left, we have to change the origin of our rectangle so that it can be on the upper left
        if (width<0){
            px=width+px;
        }

        if (length<0){
            py=length+py;
        }

        //We trace our rectangle either with fillRect if the button Fill has been pushed, and with drawRect otherwise
        if (isFilled) {
            g.fillRect(px, py, Math.abs(width), Math.abs(length));
        } else {
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(strokeSize));
            g2.drawRect(px, py, Math.abs(width), Math.abs(length));
        }
    }

    @Override
    public void setBoundingBox(int heightBB, int widthBB) {
        length = heightBB;
        width = widthBB;
    }
}
