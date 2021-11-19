package paint;

import java.awt.*;

public class Square extends Rectangle {

    public Square(int px, int py, Color c, boolean isFilled, int strokeSize) {
        super(px, py, c, isFilled, strokeSize);
    }

    @Override
    public void setBoundingBox(int heightBB, int widthBB) {
        int height = getMinAbs(heightBB, widthBB); //We get the minimum value in absolute value between the height and the width to determine size of our square
        int signedHeight = getSignedValue(heightBB, height); //We retrieve the value but with the right sign
        int signedWidth = getSignedValue(widthBB, height);
        super.setBoundingBox(signedHeight, signedWidth);
    }

    @Override
    public void setLength (int length){
        this.length = length;
    }

    @Override
    public void setWidth (int width){
        this.width = width;
    }
}
