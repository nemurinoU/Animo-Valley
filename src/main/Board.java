package main;

import player.*;
import tile.TileManager;

import java.awt.*;
import javax.swing.*;

public class Board extends JPanel implements Runnable{
    int FPS = 60;
    final int originalTileSize = 16;
    final int scaleSize = 3;

    public final int tileSize = originalTileSize * scaleSize;
    public final int maxScreenRow = 6;
    public final int maxScreenCol = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);
    TileManager tileMan = new TileManager(this);
    public Collision collision = new Collision(this);

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
            }
            
        }
    }

    public void update(){
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        tileMan.draw(g2);

        player.draw(g2);

        g2.dispose();
    }
}
