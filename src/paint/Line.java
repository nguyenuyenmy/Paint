package paint;

import java.awt.*;

public class Line extends Figure{
    protected int length;
    protected int width;

    public Line(int px, int py, Color color, int strokeSize) {
        super(color, new Point(px, py), true, strokeSize);
        length = 0;
        width = 0;
    }

    @Override
    public void draw(Graphics g) {
        int px = this.origin.getX();
        int py = this.origin.getY();
        g.setColor(this.getC());
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(strokeSize));
        g.drawLine(px, py, px+width, py+length);
    }

    @Override
    public void setBoundingBox(int heightBB, int widthBB) {
        length = heightBB;
        width = widthBB;
    }
}
