package paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Drawing extends JPanel implements MouseMotionListener, MouseListener {
    private Color color;
    private ArrayList<Figure> figureList = new ArrayList<Figure>();
    private String nameFigure;
    private int x;
    private int y;
    private boolean isFilled; //Determines whether the rectangle, ellipse, square or circle figures are going to be filled
    private int strokeSize; //Determines the size of our figures' borders

    public Drawing() {
        super();
        this.setBackground(Color.WHITE);
        this.color = Color.BLACK;
        this.nameFigure = "Rectangle";
        this.isFilled = false;
    }

    /**
     * Allows to retrieve the current color.
     * @return
     */
    public Color getColor() {
        return color;
    }

    /**
     * Allows to set the current color.
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Allows to retrieve the current figure.
     * @param nameFigure
     */
    public void setNameFigure(String nameFigure) {
        this.nameFigure = nameFigure;
    }

    /**
     * Allows to retrieve the current color.
     * @param isFilled
     */
    public void setIsFilled(boolean isFilled) {
        this.isFilled = isFilled;
    }

    /**
     * Allows to retrieve the size of the stroke.
     * @param strokeSize
     */
    public void setStrokeSize(int strokeSize){
        this.strokeSize = strokeSize;
    }

    /**
     * Allows to delete the last figure drawn.
     */
    public void doUndo(){
        if (figureList.size()>0) {
            this.figureList.remove(figureList.size() - 1);
            repaint();
        }
    }

    /**
     * Allows to save our drawing.
     * @param path
     */
    public void saveDrawing(String path){
        try{
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeInt(figureList.size());
            for(Figure f : figureList){
                oos.writeObject(f);
            }
            oos.close();
        }
        catch (Exception e){
            System.out.println("Problemos !");
        }
    }

    /**
     * Allows to open a drawing that has been drawn before.
     * @param path
     */
    public void openDrawing(String path){
        try{
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            int figureSize = ois.readInt();
            figureList = new ArrayList<Figure>();
            for (int i = 0; i < figureSize; i++) {
                Figure figure = (Figure) ois.readObject();
                figureList.add(figure);
            }
            ois.close();
            repaint();
        }
        catch (Exception e){
            System.out.println("Problemos !");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Figure figure: figureList) {
            figure.draw(g);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();

        Figure currentFigure = null;
        switch(nameFigure){
            case Window.RECTANGLE :
                currentFigure = new Rectangle(this.x, this.y, color, isFilled, strokeSize);
                break;

            case Window.ELLIPSE :
                currentFigure = new Ellipse(this.x,this.y,color, isFilled, strokeSize);
                break;

            case Window.SQUARE :
                currentFigure = new Square(this.x, this.y, color, isFilled, strokeSize);
                break;

            case Window.CIRCLE :
                currentFigure = new Circle(this.x, this.y, color, isFilled, strokeSize);
                break;

            case Window.LINE:
                currentFigure = new Line(this.x, this.y, color, strokeSize);
                break;
        }
        figureList.add(currentFigure);
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        int length = e.getX()-this.x;
        int width = e.getY()-this.y;

        //System.out.println("mouseDragged() length : " + length + " width : " + width); //Verifying that the mouseDragged is taken into account

        Figure currentFigure = figureList.get(figureList.size() - 1);

        if (currentFigure != null) {
            currentFigure.setBoundingBox(width, length);
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}
}
