package player;

import main.Board;
import main.KeyHandler;
import java.awt.*;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Player extends Sprite{

    Board board;
    KeyHandler keyH;

    public Player(Board board, KeyHandler keyH){
        
        this.board = board;
        this.keyH = keyH;

        setValues();
        getPlayerSprite();
    }

    public void getPlayerSprite(){
        try {
            
            down0 = ImageIO.read(getClass().getResourceAsStream("spliced_sprites/tile001.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("spliced_sprites/tile000.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("spliced_sprites/tile002.png"));
            left0 = ImageIO.read(getClass().getResourceAsStream("spliced_sprites/tile016.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("spliced_sprites/tile015.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("spliced_sprites/tile017.png"));
            right0 = ImageIO.read(getClass().getResourceAsStream("spliced_sprites/tile031.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("spliced_sprites/tile030.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("spliced_sprites/tile032.png"));
            up0 = ImageIO.read(getClass().getResourceAsStream("spliced_sprites/tile046.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("spliced_sprites/tile045.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("spliced_sprites/tile047.png"));

        } catch (Exception e) {
            System.out.println("boang");
        }
        
    }

    public void setValues(){
        x = 200;
        y = 200;
        speed = 4;
        direction = "down";
    }

    public void update(){
        
        if (keyH.upPressed == true|| keyH.downPressed ==true|| 
        keyH.leftPressed ==true|| keyH.rightPressed==true){
            
            if (keyH.upPressed == true){
                direction = "up";
                y -= speed;
            }
            else if (keyH.downPressed == true){
                direction = "down";
                y += speed;
            }
            else if (keyH.leftPressed == true){
                direction = "left";
                x -= speed;
            }
            else if (keyH.rightPressed == true){
                direction = "right";
                x += speed;
            }
    
            counter++;
            if (counter > 10){
                if (spriteID == 0){
                    spriteID = 1;
                }
                else if (spriteID == 1){
                    spriteID = 2;
                }
                else if (spriteID == 2){
                    spriteID = 1;
                }
                
                counter = 0;
            }
        }
        else{
            spriteID = 0;
        }
    }
    public void draw(Graphics2D g2){
        /*g2.setColor(Color.white);
        g2.fillRect(x, y, board.tileSize, board.tileSize);*/

        BufferedImage image = null;

        switch (direction){
            case "up":
                if(spriteID == 0)image = up0;
                if (spriteID == 1)image = up1;
                if (spriteID == 2)image = up2;
                break;
            case "down":
                if(spriteID == 0)image = down0;
                if (spriteID == 1)image = down1;
                if (spriteID == 2)image = down2;
                break;
            case "left":
                if(spriteID == 0)image = left0;
                if (spriteID == 1)image = left1;
                if (spriteID == 2)image = left2;
                break;
            case "right":
                if(spriteID == 0)image = right0; 
                if (spriteID == 1)image = right1;
                if (spriteID == 2)image = right2;
                break;
        }

        g2.drawImage(image, x, y, board.tileSize, board.tileSize, null);
    }
}