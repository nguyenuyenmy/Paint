package paint;

import java.awt.*;

public class Ellipse extends Figure {
    protected int semiAxisX;
    protected int semiAxisY;

    public Ellipse(int px, int py, Color c, boolean isFilled, int strokeSize) {
        super(c, new Point(px, py), isFilled, strokeSize);
        semiAxisX = 0;
        semiAxisY = 0;
    }

    /**
     * Allows to get the perimeter of our ellispe.
     * @return
     */
    public double getPerimeter(){
        double a = this.semiAxisX;
        double b = this.semiAxisY;
        double product = (3*a+b)*(a+3*b);

        return Math.PI * (3*(a+b)-Math.sqrt(product));
    }

    /**
     * Allows to get the surface of our ellispe.
     * @return
     */
    public double getSurface (){
        return Math.PI * this.semiAxisX * this.semiAxisY;
    }

    /**
     * Allows to set the semiAxisX.
     * @return
     */
    public void setSemiAxisX(int semiAxisX) {
        this.semiAxisX = semiAxisX;
    }

    /**
     * Allows to set the semiAxisY.
     * @return
     */
    public void setSemiAxisY(int semiAxisY) {
        this.semiAxisY = semiAxisY;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(this.getC());

        //We draw our ellipse so that the origin point is in the middle of our figure
        int absSemiAxisX = Math.abs(semiAxisX);
        int x = this.origin.getX() - absSemiAxisX;
        int absSemiAxisY = Math.abs(semiAxisY);
        int y = this.origin.getY() - absSemiAxisY;

        //We trace our ellipse either with fillOval if the button Fill has been pushed, and with drawOval otherwise
        if (isFilled) {
            g.fillOval(x, y, 2* absSemiAxisX, 2* absSemiAxisY);
        } else {
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(strokeSize));
            g.drawOval(x, y, 2 * absSemiAxisX, 2 * absSemiAxisY);
        }
    }

    @Override
    public void setBoundingBox(int heightBB, int widthBB) {
        semiAxisY = heightBB;
        semiAxisX = widthBB;
    }
}
