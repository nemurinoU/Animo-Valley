package player;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;
/***
 * <p>
 * This class is the controller for the sprites found in the game.
 * </p>
 *
 * @author  Francis Martinez, Richard Pecson Jr.
 * @version a0.0.8
 * @since   2022-11-28
 */
public class Sprite {

    private int x, y, speed;
    /** These are the images of the animation sprite */
    private BufferedImage up0, up1, up2, down0, down1, down2, left0, left1, left2, right0, right1, right2;
    /** This is a string indicating the direction the player is facing */
    private String direction;
    
    private int counter;
    private int spriteID;

    private Rectangle spriteArea;
    private boolean collisionOn;
    
    public Sprite(){
        this.counter = 0;
        this.spriteID = 1;
        this.collisionOn = false;
    }


    
    /** 
     * @return int
     */
    public int getX() {
        return this.x;
    }

    
    /** 
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    
    /** 
     * @return int
     */
    public int getY() {
        return this.y;
    }

    
    /** 
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    
    /** 
     * @return int
     */
    public int getSpeed() {
        return this.speed;
    }

    
    /** 
     * @param speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    
    /** 
     * @return BufferedImage
     */
    public BufferedImage getUp0() {
        return this.up0;
    }

    
    /** 
     * @param up0
     */
    public void setUp0(BufferedImage up0) {
        this.up0 = up0;
    }

    
    /** 
     * @return BufferedImage
     */
    public BufferedImage getUp1() {
        return this.up1;
    }

    
    /** 
     * @param up1
     */
    public void setUp1(BufferedImage up1) {
        this.up1 = up1;
    }

    
    /** 
     * @return BufferedImage
     */
    public BufferedImage getUp2() {
        return this.up2;
    }

    
    /** 
     * @param up2
     */
    public void setUp2(BufferedImage up2) {
        this.up2 = up2;
    }

    
    /** 
     * @return BufferedImage
     */
    public BufferedImage getDown0() {
        return this.down0;
    }

    
    /** 
     * @param down0
     */
    public void setDown0(BufferedImage down0) {
        this.down0 = down0;
    }

    
    /** 
     * @return BufferedImage
     */
    public BufferedImage getDown1() {
        return this.down1;
    }

    
    /** 
     * @param down1
     */
    public void setDown1(BufferedImage down1) {
        this.down1 = down1;
    }

    
    /** 
     * @return BufferedImage
     */
    public BufferedImage getDown2() {
        return this.down2;
    }

    
    /** 
     * @param down2
     */
    public void setDown2(BufferedImage down2) {
        this.down2 = down2;
    }

    
    /** 
     * @return BufferedImage
     */
    public BufferedImage getLeft0() {
        return this.left0;
    }

    
    /** 
     * @param left0
     */
    public void setLeft0(BufferedImage left0) {
        this.left0 = left0;
    }

    
    /** 
     * @return BufferedImage
     */
    public BufferedImage getLeft1() {
        return this.left1;
    }

    
    /** 
     * @param left1
     */
    public void setLeft1(BufferedImage left1) {
        this.left1 = left1;
    }

    
    /** 
     * @return BufferedImage
     */
    public BufferedImage getLeft2() {
        return this.left2;
    }

    
    /** 
     * @param left2
     */
    public void setLeft2(BufferedImage left2) {
        this.left2 = left2;
    }

    
    /** 
     * @return BufferedImage
     */
    public BufferedImage getRight0() {
        return this.right0;
    }

    
    /** 
     * @param right0
     */
    public void setRight0(BufferedImage right0) {
        this.right0 = right0;
    }

    
    /** 
     * @return BufferedImage
     */
    public BufferedImage getRight1() {
        return this.right1;
    }

    
    /** 
     * @param right1
     */
    public void setRight1(BufferedImage right1) {
        this.right1 = right1;
    }

    
    /** 
     * @return BufferedImage
     */
    public BufferedImage getRight2() {
        return this.right2;
    }

    
    /** 
     * @param right2
     */
    public void setRight2(BufferedImage right2) {
        this.right2 = right2;
    }

    
    /** 
     * @return String
     */
    public String getDirection() {
        return this.direction;
    }

    
    /** 
     * @param direction
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    
    /** 
     * @return int
     */
    public int getCounter() {
        return this.counter;
    }

    
    /** 
     * @param counter
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }

    
    /** 
     * @return int
     */
    public int getSpriteID() {
        return this.spriteID;
    }

    
    /** 
     * @param spriteID
     */
    public void setSpriteID(int spriteID) {
        this.spriteID = spriteID;
    }

    
    /** 
     * @return Rectangle
     */
    public Rectangle getSpriteArea() {
        return this.spriteArea;
    }

    
    /** 
     * @param spriteArea
     */
    public void setSpriteArea(Rectangle spriteArea) {
        this.spriteArea = spriteArea;
    }

    
    /** 
     * @return boolean
     */
    public boolean getCollisionOn() {
        return this.collisionOn;
    }

    
    /** 
     * @param collisionOn
     */
    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
    }

}
