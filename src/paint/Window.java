package paint;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Window extends JFrame implements ActionListener {

    public static final String BLACK = "Black";
    public static final String RED = "Red";
    public static final String GREEN = "Green";
    public static final String BLUE = "Blue";
    public static final String YELLOW = "Yellow";
    public static final String PINK = "Pink";
    public static final String MAGENTA = "Magenta";
    public static final String ORANGE = "Orange";
    public static final String WHITE = "White";
    public static final String GRAY = "Gray";
    public static final String RECTANGLE = "Rectangle";
    public static final String ELLIPSE = "Ellipse";
    public static final String SQUARE = "Square";
    public static final String CIRCLE = "Circle";
    public static final String LINE = "Line";
    public static final String FILL = "Fill";
    public static final String OUTLINE = "Outline";
    public static final String BACKGROUND = "Background";
    public static final String UNDO = "Undo";
    public static final String NEW = "New";
    public static final String OPEN = "Open";
    public static final String SAVE = "Save";
    public static final String QUIT = "Quit";
    public static final String ABOUT = "About";
    public static final String AUTHORS = "Authors";
    public static final String FILE = "File";
    private boolean isSaved = true;

    public Window(String Title, int x, int y){
        super(Title);
        this.setSize(x,y);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Creating our menubar
        JMenuBar jMenuBar = new JMenuBar();
        this.setJMenuBar(jMenuBar); //We create a menubar that we then have to set

        //Adding the different categories of our menu bar
        //Creating our menu File
        createFileMenu(jMenuBar);

        //Creating our menu About
        createAboutMenu(jMenuBar);

        //Creating our JPanel
        Container contentPanel = this.getContentPane();

        //Creating one JPanel that we are going to separate in three
        createButtonPanel(contentPanel);

        //Creating our drawing panel
        createDrawingPanel(contentPanel);

        this.setVisible(true);
    }

    /**
     * Creating our menu File.
     * @param jMenuBar
     */
    private void createFileMenu(JMenuBar jMenuBar) {
        JMenu fileJMenu = new JMenu(FILE);
        jMenuBar.add(fileJMenu);

        //Adding items to the File menu
        JMenuItem nouveau = new JMenuItem (NEW);
        fileJMenu.add(nouveau);
        nouveau.addActionListener(this);

        JMenuItem openJMenuItem = new JMenuItem (OPEN);
        fileJMenu.add(openJMenuItem);
        openJMenuItem.addActionListener(this);

        JMenuItem saveJMenuItem = new JMenuItem (SAVE);
        KeyStroke ctrlSKeyStroke = KeyStroke.getKeyStroke("control S"); //Add the shortcut
        saveJMenuItem.setAccelerator(ctrlSKeyStroke);
        fileJMenu.add(saveJMenuItem);
        saveJMenuItem.addActionListener(this);

        JMenuItem cancelJMenuItem = new JMenuItem(UNDO);
        KeyStroke ctrlZKeyStroke = KeyStroke.getKeyStroke("control Z"); //Add the shortcut
        cancelJMenuItem.setAccelerator(ctrlZKeyStroke);
        fileJMenu.add(cancelJMenuItem);
        cancelJMenuItem.addActionListener(this);

        fileJMenu.add(new JSeparator()); // SEPARATOR

        JMenuItem quitJMenuItem = new JMenuItem (QUIT);
        fileJMenu.add(quitJMenuItem);
        quitJMenuItem.addActionListener(this);
    }

    /**
     * Creating our menu About.
     * @param jMenuBar
     */
    private void createAboutMenu(JMenuBar jMenuBar) {
        JMenu aboutJMenu = new JMenu(ABOUT);
        jMenuBar.add(aboutJMenu);

        //Adding items to that About menu
        JMenuItem authorsJMenuItem = new JMenuItem(AUTHORS);
        aboutJMenu.add(authorsJMenuItem);
        authorsJMenuItem.addActionListener(this);
    }

    /**
     * Creating our button panel.
     * @param contentPanel
     */
    private void createButtonPanel(Container contentPanel) {
        JPanel buttonPanel = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
        buttonPanel.setLayout(flowLayout);
        contentPanel.add(buttonPanel,"South");

        //Creating our color panel
        createColourPanel(buttonPanel);

        //Creating our figure panel
        createFigurePanel(buttonPanel);

        //Creating our stroke size panel
        createStrokePanel(buttonPanel);
    }

    /**
     * Creating our drawing panel.
     * @param contentPanel
     */
    private void createDrawingPanel(Container contentPanel) {
        Drawing drawing = new Drawing();
        contentPanel.add(drawing);
        drawing.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                myMousePressed(e);
            }
        });
        drawing.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {myMouseDragged(e);
            }
        });
    }

    private void myMousePressed(MouseEvent e) {
        Drawing drawing = getDrawing();
        drawing.mousePressed(e);
    }

    private void myMouseDragged(MouseEvent e){
        Drawing drawing = getDrawing();
        isSaved = false;
        drawing.mouseDragged(e);
    }

    /**
     * Creating our colour panel.
     * @param buttonPanel
     */
    private void createColourPanel(JPanel buttonPanel) {
        JPanel colorPanel = new JPanel();
        buttonPanel.add(colorPanel,"East");
        colorPanel.setLayout(new GridLayout(2,4));

        createColorButton(colorPanel, BLACK, Color.WHITE);
        createColorButton(colorPanel, GRAY);
        createColorButton(colorPanel, BLUE, Color.WHITE);
        createColorButton(colorPanel, RED);
        createColorButton(colorPanel, GREEN);
        createColorButton(colorPanel, MAGENTA);
        createColorButton(colorPanel, PINK);
        createColorButton(colorPanel, ORANGE);
        createColorButton(colorPanel, YELLOW);
        createColorButton(colorPanel, WHITE);
    }

    /**
     * Allows us to create color buttons by setting the back/foreground color, the name, and to add an ActionListener.
     * @param colorPanel
     * @param textButton
     * @param foreGroundColor
     */
    private void createColorButton(JPanel colorPanel, String textButton,  Color foreGroundColor) {
        JButton button = new JButton(textButton);
        Color backGroundColor = getColor(textButton);
        button.setBackground(backGroundColor);
        button.setForeground(foreGroundColor);
        colorPanel.add(button);
        button.addActionListener(this);
    }
    private void createColorButton(JPanel colorPanel, String textButton) {
        createColorButton(colorPanel, textButton, Color.BLACK);
    }

    /**
     * Creating our figure panel.
     * @param buttonPanel
     */
    private void createFigurePanel(JPanel buttonPanel) {
        JPanel figurePanel = new JPanel();
        buttonPanel.add(figurePanel,"Center");
        figurePanel.setLayout(new GridLayout(2,8));

        addButton(figurePanel, RECTANGLE);
        addButton(figurePanel, ELLIPSE);
        addButton(figurePanel, SQUARE);
        addButton(figurePanel, CIRCLE);
        addButton(figurePanel, LINE);
        addButton(figurePanel, FILL);
        addButton(figurePanel, OUTLINE);
        addButton(figurePanel, BACKGROUND);
    }

    /**
     * Allows us to add buttons to our figure panel.
     * @param figurePanel
     * @param label
     */
    private void addButton(JPanel figurePanel, String label) {
        JButton button = new JButton(label);
        button.addActionListener(this);
        figurePanel.add(button);
    }

    /**
     * Creating our stroke panel (slider).
     * @param buttonPanel
     */
    private void createStrokePanel(JPanel buttonPanel) {
        JPanel strokePanel = new JPanel();
        buttonPanel.add(strokePanel,"West");
        strokePanel.setLayout(new GridLayout(1,1));

        JSlider slider = new JSlider(1, 5);
        slider.setMajorTickSpacing (1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        slider.setValue(1); //We initialise our stroke size to 1

        Border blackLine = BorderFactory.createTitledBorder("Brush size");
        slider.setBorder(blackLine);
        strokePanel.add(slider);

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                strokeSizeChanged(e);
            }
        });
    }

    private void strokeSizeChanged(ChangeEvent e){
        JSlider slider = (JSlider) e.getSource();
        Drawing drawing = getDrawing();
        drawing.setStrokeSize(slider.getValue());
    }

    /**
     * Allows us to retrieve the drawing that we created and put in the contentPanel.
     * @return
     */
    private Drawing getDrawing() {
        Container contentPanel = this.getContentPane();
        return (Drawing) contentPanel.getComponent(1);
    }

    /**
     * Allows us to convert a String color to a Color color
     * @param col
     * @return
     */
    private Color getColor(String col) {
        Color color;
        switch (col) {
            case BLACK:
                color = Color.BLACK;
                break;
            case BLUE:
                color = Color.BLUE;
                break;
            case GREEN:
                color = Color.GREEN;
                break;
            case YELLOW:
                color = Color.YELLOW;
                break;
            case MAGENTA:
                color = Color.MAGENTA;
                break;
            case ORANGE:
                color = Color.ORANGE;
                break;
            case PINK:
                color = Color.PINK;
                break;
            case RED:
                color = Color.RED;
                break;
            case WHITE:
                color = Color.WHITE;
                break;
            case GRAY:
                color = Color.GRAY;
                break;
            default:
                color = null;
        }
        return color;
    }

    /**
     * Allows to choose the path we are going to save or open our work.
     * @param chooserTitle
     * @param approveButtonText
     * @return
     */
    private String getPath(String chooserTitle, String approveButtonText){
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File(".\\temp"));
        chooser.setDialogTitle(chooserTitle);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        if(approveButtonText != null) {
            chooser.setApproveButtonText(approveButtonText);
        }

        String path = null;
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            path = chooser.getSelectedFile().toString();
        }
        else {
            System.out.println("No path was selected.");
        }
        return path;
    }

    /**
     * Method to quit the work we're working on.
     */
    private void quitWork() {
        if (!isSaved) {
            JOptionPane saved = new JOptionPane();
            int confirmNotSaved = saved.showConfirmDialog(null, "You haven't saved your work yet. Do you still want to quit?", "Work not saved.",JOptionPane.YES_NO_OPTION);
            if (confirmNotSaved == JOptionPane.YES_OPTION) {
                isSaved=true;
            }
        }
        if (isSaved) {
            this.dispose();
        }
    }

    /**
     * Method to open a work that has already been drawn before.
     * @param drawing
     */
    private void openWork(Drawing drawing) {
        boolean openSaved = isSaved;
        if (!isSaved) {
            JOptionPane saved = new JOptionPane();
            int confirmNotSaved = saved.showConfirmDialog(null, "You haven't saved your work yet. Do you still want to open an existent work?", "Work not saved.",JOptionPane.YES_NO_OPTION);
            if (confirmNotSaved == JOptionPane.YES_OPTION) {
                openSaved = true;
            }
        }
        if (openSaved) {
            String openPath = getPath("Open", null);
            if (openPath != null) {
                drawing.openDrawing(openPath);
                isSaved = true;
            }
        }
    }

    /**
     * Method to save the work we're working on.
     * @param drawing
     */
    private void saveWork(Drawing drawing) {
        String savePath = getPath("Save", "Save");
        if (savePath != null) {
            drawing.saveDrawing(savePath);
            isSaved = true;
        }
    }

    /**
     * Method to open a new blank work.
     */
    private void openNewWork() {
        if (!isSaved) {
            JOptionPane saved = new JOptionPane();
            int confirmNotSaved = saved.showConfirmDialog(null, "You haven't saved your work yet. Do you still want to open a new work?", "Work not saved.",JOptionPane.YES_NO_OPTION);
            if (confirmNotSaved == JOptionPane.YES_OPTION) {
                isSaved=true;
            }
        }
        if (isSaved) {
            this.dispose();
            new Window("Paint", 900, 600);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String cmd = e.getActionCommand();
        Drawing drawing = getDrawing();
        Color color = getColor(cmd);

        if (color != null) {
            drawing.setColor(color);
        } else {
            switch (cmd){
                case RECTANGLE, ELLIPSE, SQUARE, CIRCLE, LINE :
                    drawing.setNameFigure(cmd);
                    break;

                case OUTLINE:
                    drawing.setIsFilled(false);
                    break;

                case BACKGROUND:
                    drawing.setBackground(drawing.getColor());
                    break;

                case FILL:
                    drawing.setIsFilled(true);
                    break;

                case AUTHORS :
                    JOptionPane info = new JOptionPane();
                    info.showInternalMessageDialog(info, "Author : Uyen-My Nguyen", "information",JOptionPane.INFORMATION_MESSAGE);
                    break;

                case NEW :
                    openNewWork();
                    break;

                case SAVE :
                    saveWork(drawing);
                    break;

                case UNDO:
                    drawing.doUndo();
                    break;

                case OPEN :
                    openWork(drawing);
                    break;

                case QUIT :
                    quitWork();
                    break;
            }
        }
    }
}
