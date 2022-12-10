package tile;

import java.awt.image.BufferedImage;
public class Tile {

    /**
     * > image is the BufferedImage object we will modify
     * > hasCollision is boolean of whether tile has collision or not
     * > isUnplowed is boolean of whether tile is unplowed or not
     * > isPlowed is boolean of whether tile is plowed or not
     * > hasRock is boolean of whether tile contains a rock or not
     * > isSeeded is boolean of whether tile has been seeded or not
     * > isDry is boolean of whether tile has been watered or not
     */
    private BufferedImage image;
    private boolean hasCollision;
    private boolean isUnplowed;
    private boolean isPlowed;
    private boolean hasRock;
    private boolean isSeeded;
    private boolean isDry;

    /**
     * This constructor sets all the default values of the Tile object
     */
    public Tile(){
        this.hasCollision = false;
        this.isUnplowed = false;
        this.hasRock = false;
        this.isSeeded = false;
        this.isDry = true;
    }

    
    /** 
     * This method returns a BufferedImage object that will be painted on the screen.
     * @return BufferedImage the current image
     */
    public BufferedImage getImage() {
        return this.image;
    }

    
    /** 
     * This method sets the BufferedImage object, which is used for assigning an image to its respective index in an array.
     * @param image the new image
     */
    public void setImage(BufferedImage image) {
        this.image = image;
    }
    
    /** 
     * This method returns the value of hasCollision to check whether or not the player can pass through the tile.
     * @return boolean Boolean value of hasCollision
     */
    public boolean getHasCollision() {
        return this.hasCollision;
    }

    
    /** 
     * This method updates the value of hasCollision (true/false).
     * @param hasCollision The new hasCollision value
     */
    public void setHasCollision(boolean hasCollision) {
        this.hasCollision = hasCollision;
    }

    
    /** 
     * This method returns the value of isUnplowed to check whether or not the tile is unplowed.
     * @return boolean Boolean value of isUnplowed
     */
    public boolean getIsUnplowed() {
        return this.isUnplowed;
    }

    
    /** 
     * This method updates the value of isUnplowed (true/false).
     * @param isUnplowed The new isUnplowed value
     */
    public void setIsUnplowed(boolean isUnplowed) {
        this.isUnplowed = isUnplowed;
    }

    
    /** 
     * This method returns the value of isPlowed to check whether or not the tile is plowed to allow it to access more functions.
     * @return boolean Boolean value of isPlowed
     */
    public boolean getIsPlowed() {
        return this.isPlowed;
    }

    
    /** 
     * This method updates the value of isPlowed (true/false).
     * @param isPlowed The new isPlowed value
     */
    public void setIsPlowed(boolean isPlowed) {
        this.isPlowed = isPlowed;
    }

    
    /** 
     * This method returns the value of hasRock to check whether or not the tile contains a rock.
     * @return boolean Boolean value of hasRock
     */
    public boolean getHasRock() {
        return this.hasRock;
    }

    
    /** 
     * This method updates the value of hasRock (true/false).
     * @param hasRock The new hasRock value
     */
    public void setHasRock(boolean hasRock) {
        this.hasRock = hasRock;
    }

    
    /** 
     * This method returns the value of isSeeded to check whether or not the tile contains a planted seed.
     * @return boolean Boolean value of isSeeded
     */
    public boolean getIsSeeded() {
        return this.isSeeded;
    }

    
    /** 
     * This method updates the value of isSeeded (true/false).
     * @param isSeeded The new isSeeded value
     */
    public void setIsSeeded(boolean isSeeded) {
        this.isSeeded = isSeeded;
    }

    
    /** 
     * This method returns the value of isDry to check whether or not the tile has been watered.
     * @return boolean Boolean value of isDry
     */
    public boolean getIsDry() {
        return this.isDry;
    }

    
    /** 
     * This method updates the value of isDry (true/false).
     * @param isDry The new isDry value
     */
    public void setIsDry(boolean isDry) {
        this.isDry = isDry;
    }
}
