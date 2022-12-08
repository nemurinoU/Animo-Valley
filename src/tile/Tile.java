package tile;

import java.awt.image.BufferedImage;
public class Tile {
    
    /*private BufferedImage image;
    private boolean hasCollision = false;
    private boolean isUnplowed = false;
    private boolean isPlowed = false;
    private boolean hasRock = false;
    private boolean isSeeded = false;
    private boolean isDry = true;*/

    private BufferedImage image;
    private boolean hasCollision;
    private boolean isUnplowed;
    private boolean isPlowed;
    private boolean hasRock;
    private boolean isSeeded;
    private boolean isDry;

    public Tile(){
        this.hasCollision = false;
        this.isUnplowed = false;
        this.hasRock = false;
        this.isSeeded = false;
        this.isDry = true;
    }

    public BufferedImage getImage() {
        return this.image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public boolean isHasCollision() {
        return this.hasCollision;
    }

    public boolean getHasCollision() {
        return this.hasCollision;
    }

    public void setHasCollision(boolean hasCollision) {
        this.hasCollision = hasCollision;
    }

    public boolean getIsUnplowed() {
        return this.isUnplowed;
    }

    public void setIsUnplowed(boolean isUnplowed) {
        this.isUnplowed = isUnplowed;
    }

    public boolean getIsPlowed() {
        return this.isPlowed;
    }

    public void setIsPlowed(boolean isPlowed) {
        this.isPlowed = isPlowed;
    }

    public boolean getHasRock() {
        return this.hasRock;
    }

    public void setHasRock(boolean hasRock) {
        this.hasRock = hasRock;
    }

    public boolean getIsSeeded() {
        return this.isSeeded;
    }

    public void setIsSeeded(boolean isSeeded) {
        this.isSeeded = isSeeded;
    }

    public boolean getIsDry() {
        return this.isDry;
    }

    public void setIsDry(boolean isDry) {
        this.isDry = isDry;
    }
}
