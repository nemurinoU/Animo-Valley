package tile;

import java.awt.image.BufferedImage;
public class Tile {
    
    public BufferedImage image;
    public boolean hasCollision = false;
    public boolean isUnplowed = false;
    public boolean isPlowed = false;
    public boolean hasRock = false;
    public boolean isSeeded = false;
    public boolean isDry = true;
}
