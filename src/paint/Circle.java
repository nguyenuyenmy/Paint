package paint;

import java.awt.*;

public class Circle extends Ellipse {

    public Circle(int px, int py, Color c, boolean isFilled, int strokeSize) {
        super(px, py, c, isFilled, strokeSize);
    }

    @Override
    public void setBoundingBox(int heightBB, int widthBB) {
        int height = getMinAbs(heightBB, widthBB); //We get the minimum value in absolute value between the height and the width to determine size of our circle
        int signedHeight = getSignedValue(heightBB, height); //We retrieve the value but with the right sign
        int signedWidth = getSignedValue(widthBB, height);
        super.setBoundingBox(signedHeight, signedWidth);
    }

    @Override
    public void setSemiAxisX (int semiAxisX){
        this.semiAxisX = semiAxisX;
    }

    @Override
    public void setSemiAxisY (int semiAxisY){
        this.semiAxisY = semiAxisY;
    }

}
