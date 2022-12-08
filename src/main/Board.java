package main;

import player.*;
import tile.TileManager;
import logic.*;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

/***
 * <h1>Board</h1>
 * <p>
 * This class is used to emulate the entire game screen.
 * </p>
 *
 * @author  Francis Martinez, Richard Pecson Jr.
 * @version a0.0.8
 * @since   2022-11-28
 */
public class Board extends JPanel implements Runnable{
    /**
	* Private Variable Instantiation
	* > FPS is the frames per section, how many times are stuff rendered
    * > originalTileSize is the base size of individual tile graphics
    * > scaleSize scales up the base tiles
    * > tileSize is the new scaled up tile size
    * > maxScreenRow tells us how many rows of tiles are there in the window maximum
    * > maxScreenCol tells us how many columns of tiles are there in the window
    * > screenWidth is defined by the tileSize times the number of columns
    * > screenHeight is tileSize times the number of rows
	*/
    int FPS = 60;
    final int originalTileSize = 16;
    final int scaleSize = 3;

    public final int tileSize = originalTileSize * scaleSize;
    public final int maxScreenRow = 7;
    public final int maxScreenCol = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);
    ActionHandler actH = new ActionHandler(keyH);

    NamePrompt namePrompt = new NamePrompt ();
    InfoBar menu = new InfoBar(namePrompt.getFarmName(), namePrompt.getFarmerName());
    

    public Collision collision = new Collision(this);
    TileManager tileMan = new TileManager(this, keyH, collision);

    //Player settings
    int playerX = 200;
    int playerY = 200;
    int playerSpeed = 4;


    public Board(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.gray);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.setLayout(new GridLayout (2, 1));

        // bridges the text file and actual plotgrid at the start
        this.menu.getMyFarm().setFarmField( tileMan.updateTileGrid(menu));
    }

    public InfoBar getMenu () {
        return this.menu;
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    @Override
    public void run(){
        double drawInterval = 1000000000/FPS;
        double d = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null){

            currentTime = System.nanoTime();

            d += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (d >= 1){
                update();
                repaint();
                d--;
                drawCount++;
            }

            if (timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
                timer = 0;
                drawCount = 0;

                Display test = new Display();
                test.displayGrid (this.menu.getMyFarm().getFarmField());
            }
            
        }
    }

    public void update(){
        // update player sprite and menu/info bar above the grid
        this.menu.update();
        player.update();

        // keeps the game window focused all the time
        this.requestFocus();

        // update the PlotGrid object inside the TileManager class
        // tileMan.updateTileCopy(menu.getMyFarm().getFarmField());

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
        actH.updateLogic(menu);
    }

    public PlotLand getPlotByCoord (Coordinates coords) {
        ArrayList<PlotLand> tempGrid = this.menu.getMyFarm().getFarmField().getPlotGrid();
        PlotLand tempPlot;

        /*** 
        for (int i = 0; i < this.tempGrid.size(); i++) {
            if (tempGrid.get(i).getCoords().isEquals(coords))
        }*/
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        tileMan.draw(g2);

        player.draw(g2);

        g2.dispose();
    }

}
