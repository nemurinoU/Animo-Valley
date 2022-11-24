package main;

import java.awt.*;

import javax.swing.*;

public class Board extends JPanel implements Runnable{
    int FPS = 60;
    final int originalTileSize = 16;
    final int scaleSize = 3;

    final int tileSize = originalTileSize * scaleSize;
    final int maxScreenRow = 12;
    final int maxScreenCol = 16;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

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
        if (keyH.upPressed == true){playerY -= playerSpeed;}
        else if (keyH.downPressed == true){playerY += playerSpeed;}
        else if (keyH.leftPressed == true){playerX -= playerSpeed;}
        else if (keyH.rightPressed == true){playerX += playerSpeed;}
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();
    }
}
