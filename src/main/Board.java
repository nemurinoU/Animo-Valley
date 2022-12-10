package main;

import player.*;
import tile.TileManager;
import logic.*;

import java.awt.*;
import javax.swing.*;

/***
 * <p>
 * This class is used to emulate the entire game screen.
 * </p>
 *
 * @author  Francis Martinez, Richard Pecson Jr.
 * @version a0.0.8
 * @since   2022-11-28
 */
public class Board extends JPanel implements Runnable{
    /** FPS is the frames per section, how many times are stuff rendered */
    int FPS = 60;
    /** originalTileSize is the base size of individual tile graphics */
    private final int originalTileSize = 16;
    /** scaleSize scales up the base tiles */
    private final int scaleSize = 3;

    /** tileSize is the new scaled up tile size */
    private final int tileSize = originalTileSize * scaleSize;
    /** maxScreenRow tells us how many rows of tiles are there in the window maximum */
    private final int maxScreenRow = 7;
    /** maxScreenCol tells us how many columns of tiles are there in the window */
    private final int maxScreenCol = 12;
    /** screenWidth is defined by the tileSize times the number of columns */
    private final int screenWidth = tileSize * maxScreenCol;
    /** screenHeight is tileSize times the number of rows */
    private final int screenHeight = tileSize * maxScreenRow;
    /** success is the boolean var for successful activity */
    private boolean success;

    /** keyH is the KeyHandler object */
    KeyHandler keyH = new KeyHandler();
    /** gameThread is the Thread object for the game */
    Thread gameThread;
    /** collision is the Collision object which checks for collision */
    Collision collision = new Collision(this);
    /** player is the Player sprite */
    Player player = new Player(this, keyH, collision);
    /** actH is the ActionHandler which contains all the logic */
    ActionHandler actH = new ActionHandler(keyH);

    /** namePrompt is the NamePrompt object at the start of the game */
    NamePrompt namePrompt = new NamePrompt ();
    /** menu is the panel containing the statistics of the game */
    InfoBar menu = new InfoBar(namePrompt.getFarmName(), namePrompt.getFarmerName());
    /** toolbar is the tool bar commands shown at the bottom of the screen */
    InfoBar toolbar = new InfoBar();
    
    /** tileMan is the TileManager which contains the logic for the game map */
    TileManager tileMan = new TileManager(this, keyH, collision);

    //Player settings
    /** This is the starting x value of the player */
    int playerX = 200;
    /** This is the starting y value of the player */
    int playerY = 200;
    /** This is the speed value of the player */
    int playerSpeed = 4;


    /**
     * This is the constructor for the Board which sets the default values of the Board object.
     * <p>
     * It sets the screen size, background color, and layout.
     * <p>
     * It also sets the key listener, double buffer, and allows it to be focusable.
     */
    public Board(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.gray);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.setLayout(new GridLayout (2, 1));

        // This bridges the text file and actual plotgrid at the start
        this.menu.getMyFarm().setFarmField( tileMan.updateTileGrid(menu));
    }

    
    /** 
     * This method gets the Infobar object for the stats bar (menu)
     * @return InfoBar the menu displayed at the top of the game
     */
    public InfoBar getMenu () {
        return this.menu;
    }

    
    /** 
     * This method gets the InfoBar object for the tool bar (toolbar)
     * @return InfoBar
     */
    public InfoBar getToolbar () {
        return this.toolbar;
    }

    /**
     * This method starts the game thread by creating a Thread object and then using the start() method.
     */
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    /**
     * This method is from the Runnable interface, which is implemented to our Board.
     * <p>
     * It contains the game loop for our game, which allows it to keep it running.
     * <p>
     * It also does the painting of components or sprites on the board.
     * <p>
     * This works by calculating the Frames Per Second to be displayed on the board which is synchronized to real time.
     * <p>
     * The time measured in this method is in nanoseconds, which explains the large numbers used for the times.
     */
    @Override
    public void run(){
        double drawInterval = 1000000000/FPS;
        double d = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        /**
         * While the gameThread is not null, it will continue animating and updating the main game.
         */
        while (gameThread != null){

            currentTime = System.nanoTime();

            /*
             * This calculates the change in time to be used for the interval for the drawing of the sprites.
             */
            d += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (d >= 1){
                update();
                repaint();
                d--;
                drawCount++;
            }

            /*
             * This displays the FPS every second and resets the timer and draw
             */
            if (timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
                timer = 0;
                drawCount = 0;

                Display test = new Display();
                test.displayGrid (this.menu.getMyFarm().getFarmField());
            }
            
        }
    }

    /**
     * This method is the update method that is called in the run() method.
     * <p>
     * It is called every nanosecond of the game, so it is constantly updating the components on the screen.
     */
    public void update(){
        // update player sprite and menu/info bar above the grid
        this.menu.update();
        player.update();

        // keeps the game window focused all the time
        this.requestFocus();

        /***
         * So basically how this works is, it gets coordinates from collsion class
         * updates the "current coordinates of the player in the actionhandler for actions"
         * then it updates the plot based on logic done on the plot :)))
         */
        actH.updateLocation( collision.getCoords() );
        actH.updateMyFarm ( menu.getMyFarm());

        // will  update the logic plotgrid depending on keys pressed
        PlotGrid tempGrid = menu.getMyFarm().getFarmField();
        
        tileMan.updateTileCopy (tempGrid);
        this.success = actH.updateLogic(menu);

        // update the PlotGrid object inside the TileManager class
        tileMan.updateTileCopy(menu.getMyFarm().getFarmField());
    }

    
    /** 
     * This method is part of the Swing GUI system, which basically paints the sprites shown on the screen.
     * <p>
     * It is not called directly, rather it is used in the run() method via the repaint() method
     * @param g The graphic object
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        //Convert the Graphics to Graphics2D to allow use of the draw() methods succeeding it
        Graphics2D g2 = (Graphics2D)g;

        tileMan.draw(g2, this.success);

        player.draw(g2);

        // Destroy the Graphics2D object
        g2.dispose();
    }
    
    /** 
     * This method gets the tile size (16 * 3 = 48)
     * @return int The current tile size, which is 48 pixels
     */
    public int getTileSize() {
        return this.tileSize;
    }


    
    /** 
     * This method gets the max number of rows, which is 7 (5 for the plot land, 2 for the tree borders)
     * @return int Max number of rows containing the sprites
     */
    public int getMaxScreenRow() {
        return this.maxScreenRow;
    }


    
    /** 
     * This method gets the max number of rows, which is 12 (10 for the plot land, 2 for the tree borders)
     * @return int Max number of columns containing the sprites
     */
    public int getMaxScreenCol() {
        return this.maxScreenCol;
    }


    
    /** 
     * This method gets the screen width, which is calculated by 48 (tileSize) * 12 (screen cols) = 576 pixels
     * @return int The screen width in pixels
     */
    public int getScreenWidth() {
        return this.screenWidth;
    }

    /** 
     * This method gets the screen height, which is calculated by 48 (tileSize) * 7 (screen row) = 336 pixels
     * @return int The screen height in pixels
     */
    public int getScreenHeight() {
        return this.screenHeight;
    }

    /** 
     * This method gets the tile manager to be used in other classes.
     * @return TileManager the current tile manager
     */
    public TileManager getTileMan(){
        return this.tileMan;
    }

}
