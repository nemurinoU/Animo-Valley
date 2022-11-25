package player;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public class Sprite {

    public int x, y, speed;
    public BufferedImage up0, up1, up2, down0, down1, down2, left0, left1, left2, right0, right1, right2;
    public String direction;
    
    public int counter = 0;
    public int spriteID = 1;

    public Rectangle spriteArea;
    public boolean collisionOn = false;
    

}
