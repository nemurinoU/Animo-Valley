package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
    private boolean upPressed, downPressed, leftPressed, rightPressed;

    private boolean plowPressed, waterPressed, fertilizerPressed, pickaxePressed, shovelPressed, harvestPressed;
    private boolean seedPressed, nextSeed, prevSeed, isHeld;

    @Override
    public void keyTyped(KeyEvent e){
        
    }

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

    public boolean getIsHeld () {
        return this.isHeld;
    }

    public void setIsHeld (boolean isHeld) {
        this.isHeld = isHeld;
    }

    public boolean getNextSeed() {
        return this.nextSeed;
    }

    public boolean getPrevSeed() {
        return this.prevSeed;
    }

    public boolean getUpPressed() {
        return this.upPressed;
    }

    public boolean getDownPressed() {
        return this.downPressed;
    }

    public boolean getLeftPressed() {
        return this.leftPressed;
    }

    public boolean getRightPressed() {
        return this.rightPressed;
    }

    public boolean getPlowPressed() {
        return this.plowPressed;
    }

    public boolean getWaterPressed() {
        return this.waterPressed;
    }

    public boolean getFertilizerPressed() {
        return this.fertilizerPressed;
    }

    public boolean getPickaxePressed() {
        return this.pickaxePressed;
    }

    public boolean getShovelPressed() {
        return this.shovelPressed;
    }

    public boolean getSeedPressed() {
        return this.seedPressed;
    }
	
	public boolean getHarvestPressed() {
		return this.harvestPressed;
	}

}
