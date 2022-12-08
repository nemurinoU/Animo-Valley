package player;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public class Sprite {

    private int x, y, speed;
    private BufferedImage up0, up1, up2, down0, down1, down2, left0, left1, left2, right0, right1, right2;
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


    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public BufferedImage getUp0() {
        return this.up0;
    }

    public void setUp0(BufferedImage up0) {
        this.up0 = up0;
    }

    public BufferedImage getUp1() {
        return this.up1;
    }

    public void setUp1(BufferedImage up1) {
        this.up1 = up1;
    }

    public BufferedImage getUp2() {
        return this.up2;
    }

    public void setUp2(BufferedImage up2) {
        this.up2 = up2;
    }

    public BufferedImage getDown0() {
        return this.down0;
    }

    public void setDown0(BufferedImage down0) {
        this.down0 = down0;
    }

    public BufferedImage getDown1() {
        return this.down1;
    }

    public void setDown1(BufferedImage down1) {
        this.down1 = down1;
    }

    public BufferedImage getDown2() {
        return this.down2;
    }

    public void setDown2(BufferedImage down2) {
        this.down2 = down2;
    }

    public BufferedImage getLeft0() {
        return this.left0;
    }

    public void setLeft0(BufferedImage left0) {
        this.left0 = left0;
    }

    public BufferedImage getLeft1() {
        return this.left1;
    }

    public void setLeft1(BufferedImage left1) {
        this.left1 = left1;
    }

    public BufferedImage getLeft2() {
        return this.left2;
    }

    public void setLeft2(BufferedImage left2) {
        this.left2 = left2;
    }

    public BufferedImage getRight0() {
        return this.right0;
    }

    public void setRight0(BufferedImage right0) {
        this.right0 = right0;
    }

    public BufferedImage getRight1() {
        return this.right1;
    }

    public void setRight1(BufferedImage right1) {
        this.right1 = right1;
    }

    public BufferedImage getRight2() {
        return this.right2;
    }

    public void setRight2(BufferedImage right2) {
        this.right2 = right2;
    }

    public String getDirection() {
        return this.direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getCounter() {
        return this.counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getSpriteID() {
        return this.spriteID;
    }

    public void setSpriteID(int spriteID) {
        this.spriteID = spriteID;
    }

    public Rectangle getSpriteArea() {
        return this.spriteArea;
    }

    public void setSpriteArea(Rectangle spriteArea) {
        this.spriteArea = spriteArea;
    }

    public boolean getCollisionOn() {
        return this.collisionOn;
    }

    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
    }

}
