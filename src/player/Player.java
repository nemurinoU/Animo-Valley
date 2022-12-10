package player;

import main.Board;
import main.KeyHandler;
import main.Collision;
import java.awt.*;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
/***
 * <h1>Player</h1>
 * <p>
 * This class is used to create a Player object that the player of the game can control.
 * <p>
 * It extends the Sprite class which contains the needed methods for the Player sprite.
 *
 * @author  Francis Martinez, Richard Pecson Jr.
 * @version a0.0.8
 * @since   2022-12-01
 */
public class Player extends Sprite{

    Board board;
    KeyHandler keyH;
    Collision colz;

    /**
     * This constructor sets the default attributes to be used in other classes
     * @param board The board that shall be updated
     * @param keyH The keyHandler that allows us to use keys for the interactions
     * @param colz The collision checker which constantly checks for collision
     */
    public Player(Board board, KeyHandler keyH, Collision colz){
        this.board = board;
        this.keyH = keyH;
        this.colz = colz;

        //Create a rectangle inside of the sprite with dimensions for the collision
        setSpriteArea(new Rectangle(8, 16, 32, 32));

        defaultValues();
        getPlayerSprite();
    }

    /**
     * This method gets the player sprite images.
     * It uses the try-catch statement because if there is no sprite to be set, it would return an error
     */
    public void getPlayerSprite(){
        try {
            
            setDown0(ImageIO.read(getClass().getResourceAsStream("spliced_sprites/tile001.png")));
            setDown1(ImageIO.read(getClass().getResourceAsStream("spliced_sprites/tile000.png")));
            setDown2(ImageIO.read(getClass().getResourceAsStream("spliced_sprites/tile002.png")));
            setLeft0(ImageIO.read(getClass().getResourceAsStream("spliced_sprites/tile016.png")));
            setLeft1(ImageIO.read(getClass().getResourceAsStream("spliced_sprites/tile015.png")));
            setLeft2(ImageIO.read(getClass().getResourceAsStream("spliced_sprites/tile017.png")));
            setRight0(ImageIO.read(getClass().getResourceAsStream("spliced_sprites/tile031.png")));
            setRight1(ImageIO.read(getClass().getResourceAsStream("spliced_sprites/tile030.png")));
            setRight2(ImageIO.read(getClass().getResourceAsStream("spliced_sprites/tile032.png")));
            setUp0(ImageIO.read(getClass().getResourceAsStream("spliced_sprites/tile046.png")));
            setUp1(ImageIO.read(getClass().getResourceAsStream("spliced_sprites/tile045.png")));
            setUp2(ImageIO.read(getClass().getResourceAsStream("spliced_sprites/tile047.png")));

        } catch (Exception e) {
            System.out.println(e);
        }
        
    }

    /**
     * This method sets the default values of the player.
     */
    public void defaultValues(){
        //These two set the starting position of the player on the farm.
        setX(250);
        setY(100);
        //This is the speed of the player
        setSpeed(4);;
        //The player looks down by default
        setDirection("down");
    }

    /**
     * This update() method is called every frame, whenever the player moves.
     * If the player presses W, they go up.
     * If the player presses A, they go left. 
     * If the player presses D, they go right. 
     * If the player presses S, they go down.
     */
    public void update(){
        
        if (keyH.getUpPressed() == true|| keyH.getDownPressed() == true|| 
        keyH.getLeftPressed() ==true|| keyH.getRightPressed() == true){
            
            if (keyH.getUpPressed() == true){
                setDirection("up");
                //y -= speed;
            }
            else if (keyH.getDownPressed() == true){
                setDirection("down");
                //y += speed;
            }
            else if (keyH.getLeftPressed() == true){
                setDirection("left");
                //x -= speed;
            }
            else if (keyH.getRightPressed() == true){
                setDirection("right");
                //x += speed;
            }
    
            //There is no collision by default
            setCollisionOn(false);
            //But it checks for collision i.e. the trees
            colz.checkTile(this);

            //This is for the direction of the player
            if (getCollisionOn() == false){
                switch (getDirection()){
                    case "up":
                        //y -= speed;
                        setY(getY() - getSpeed());
                        break;
                    case "down":
                        //y += speed;
                        setY(getY() + getSpeed());
                        break;
                    case "left":
                        //x -= speed;
                        setX(getX() - getSpeed());
                        break;
                    case "right":
                        //x += speed;
                        setX(getX() + getSpeed());
                        break;
                }
            }

            //This is the animation for the walking
            setCounter(getCounter() + 1);
            if (getCounter() > 10){
                if (getSpriteID() == 0){
                    setSpriteID(1);
                }
                else if (getSpriteID() == 1){
                    setSpriteID(2);
                }
                else if (getSpriteID() == 2){
                    setSpriteID(1);
                }
                
                setCounter(0);;
            }
        }
        else{
            setSpriteID(0);
        }
    }
    
    /** 
     * This is the draw() method used in the update() method in Board.java
     * @param g2 The 2D graphics to be drawn
     */
    public void draw(Graphics2D g2){

        //Set BufferedImage to null first
        BufferedImage image = null;

        switch (getDirection()){
            case "up":
                if(getSpriteID() == 0)image = getUp0();
                if (getSpriteID() == 1)image = getUp1();
                if (getSpriteID() == 2)image = getUp2();
                break;
            case "down":
                if(getSpriteID() == 0)image = getDown0();
                if (getSpriteID() == 1)image = getDown1();
                if (getSpriteID() == 2)image = getDown2();
                break;
            case "left":
                if(getSpriteID() == 0)image = getLeft0();
                if (getSpriteID() == 1)image = getLeft1();
                if (getSpriteID() == 2)image = getLeft2();
                break;
            case "right":
                if(getSpriteID() == 0)image = getRight0();
                if (getSpriteID() == 1)image = getRight1();
                if (getSpriteID() == 2)image = getRight2();
                break;
        }

        //Draw the player sprite using this method, which is called constantly
        g2.drawImage(image, getX(), getY(), board.getTileSize(), board.getTileSize(), null);
    }
}