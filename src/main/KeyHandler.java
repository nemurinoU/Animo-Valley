package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
    private boolean upPressed, downPressed, leftPressed, rightPressed;

    private boolean plowPressed, waterPressed, fertilizerPressed, pickaxePressed, shovelPressed, harvestPressed;
    private boolean seedPressed, nextSeed, prevSeed, isHeld;

    
    /** 
     * This is a built-in method from the KeyListener interface. 
     * <p>
     * This method is called when a character is typed (which is never)
     * @param e the KeyEvent object
     */
    @Override
    public void keyTyped(KeyEvent e){
        
    }

    
    /** 
     * This is a built-in method from the KeyListener interface. 
     * <p>
     * This method is called when the user presses a key
     * @param e the KeyEvent object
     */
    @Override
    public void keyPressed(KeyEvent e){
        
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W){
            upPressed = true;
        }

        if (code == KeyEvent.VK_S){
            downPressed = true;
        }

        if (code == KeyEvent.VK_A){
            leftPressed = true;
        }

        if (code == KeyEvent.VK_D){
            rightPressed = true;
        }

        if (code == KeyEvent.VK_1){
            plowPressed = true;
        }

        if (code == KeyEvent.VK_2){
            waterPressed = true;
        }

        if (code == KeyEvent.VK_3){
            fertilizerPressed = true;
        }

        if (code == KeyEvent.VK_4){
            pickaxePressed = true;
        }

        if (code == KeyEvent.VK_5){
            shovelPressed = true;
        } 
        
        if (code == KeyEvent.VK_SPACE){
            harvestPressed = true;
        }

        if (code == KeyEvent.VK_0){
            seedPressed = true;
        }

        if (code == KeyEvent.VK_EQUALS) {
            nextSeed = true;
        }

        if (code == KeyEvent.VK_MINUS) {
            prevSeed = true;
        }

        
    }

    
    /** 
     * This is a built-in method from the KeyListener interface. 
     * <p>
     * This method is called when the user releases a key
     * @param e the KeyEvent object
     */
    @Override
    public void keyReleased(KeyEvent e){
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W){
            upPressed = false;
        }

        if (code == KeyEvent.VK_S){
            downPressed = false;
        }

        if (code == KeyEvent.VK_A){
            leftPressed = false;
        }

        if (code == KeyEvent.VK_D){
            rightPressed = false;
        }

        if (code == KeyEvent.VK_1){
            plowPressed = false;
        }

        if (code == KeyEvent.VK_2){
            waterPressed = false;
        }

        if (code == KeyEvent.VK_3){
            fertilizerPressed = false;
        }

        if (code == KeyEvent.VK_4){
            pickaxePressed = false;
        }

        if (code == KeyEvent.VK_5){
            shovelPressed = false;
        } 
        
        if (code == KeyEvent.VK_SPACE){
            harvestPressed = false;
        }

        if (code == KeyEvent.VK_0){
            seedPressed = false;
        }

        if (code == KeyEvent.VK_EQUALS) {
            nextSeed = false;
        }

        if (code == KeyEvent.VK_MINUS) {
            prevSeed = false;
        }

        this.isHeld = false;

    }

    
    /** 
     * @return boolean
     */
    public boolean getIsHeld () {
        return this.isHeld;
    }

    
    /** 
     * @param isHeld
     */
    public void setIsHeld (boolean isHeld) {
        this.isHeld = isHeld;
    }

    
    /** 
     * @return boolean
     */
    public boolean getNextSeed() {
        return this.nextSeed;
    }

    
    /** 
     * @return boolean
     */
    public boolean getPrevSeed() {
        return this.prevSeed;
    }

    
    /** 
     * @return boolean
     */
    public boolean getUpPressed() {
        return this.upPressed;
    }

    
    /** 
     * @return boolean
     */
    public boolean getDownPressed() {
        return this.downPressed;
    }

    
    /** 
     * @return boolean
     */
    public boolean getLeftPressed() {
        return this.leftPressed;
    }

    
    /** 
     * @return boolean
     */
    public boolean getRightPressed() {
        return this.rightPressed;
    }

    
    /** 
     * @return boolean
     */
    public boolean getPlowPressed() {
        return this.plowPressed;
    }

    
    /** 
     * @return boolean
     */
    public boolean getWaterPressed() {
        return this.waterPressed;
    }

    
    /** 
     * @return boolean
     */
    public boolean getFertilizerPressed() {
        return this.fertilizerPressed;
    }

    
    /** 
     * @return boolean
     */
    public boolean getPickaxePressed() {
        return this.pickaxePressed;
    }

    
    /** 
     * @return boolean
     */
    public boolean getShovelPressed() {
        return this.shovelPressed;
    }

    
    /** 
     * @return boolean
     */
    public boolean getSeedPressed() {
        return this.seedPressed;
    }
	
	
    /** 
     * @return boolean
     */
    public boolean getHarvestPressed() {
		return this.harvestPressed;
	}

}
