package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
    private boolean upPressed, downPressed, leftPressed, rightPressed;
    private boolean spacePressed, uPressed, pPressed, iPressed, oPressed, yPressed;

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

        if (code == KeyEvent.VK_SPACE){
            spacePressed = true;
        }

        if (code == KeyEvent.VK_U){
            uPressed = true;
        }

        if (code == KeyEvent.VK_P){
            pPressed = true;
        }

        if (code == KeyEvent.VK_I){
            iPressed = true;
        }

        if (code == KeyEvent.VK_O) {
            oPressed = true;
        }

        if (code == KeyEvent.VK_Y) {
            yPressed = true;
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

        if (code == KeyEvent.VK_SPACE){
            spacePressed = false;
        }

        if (code == KeyEvent.VK_U){
            uPressed = false;
        }

        if (code == KeyEvent.VK_P){
            pPressed = false;
        }
        
        if (code == KeyEvent.VK_I){
            iPressed = false;
        }

        if (code == KeyEvent.VK_O) {
            oPressed = false;
        }

        if (code == KeyEvent.VK_Y) {
            yPressed = false;
        }
    }

    public boolean getYPressed () {
        return this.yPressed;
    }

    public void setYPressed (boolean yPressed) {
        this.yPressed = yPressed;
    }
    
    public boolean getOPressed () {
        return this.oPressed;
    }

    public void setOPressed (boolean oPressed) {
        this.oPressed = oPressed;
    }

    public boolean getUpPressed() {
        return this.upPressed;
    }

    public void setUpPressed(boolean upPressed) {
        this.upPressed = upPressed;
    }

    public boolean getDownPressed() {
        return this.downPressed;
    }

    public void setDownPressed(boolean downPressed) {
        this.downPressed = downPressed;
    }

    public boolean getLeftPressed() {
        return this.leftPressed;
    }

    public void setLeftPressed(boolean leftPressed) {
        this.leftPressed = leftPressed;
    }

    public boolean getRightPressed() {
        return this.rightPressed;
    }

    public void setRightPressed(boolean rightPressed) {
        this.rightPressed = rightPressed;
    }

    public boolean getSpacePressed() {
        return this.spacePressed;
    }

    public void setSpacePressed(boolean spacePressed) {
        this.spacePressed = spacePressed;
    }

    public boolean getUPressed() {
        return this.uPressed;
    }

    public void setUPressed(boolean uPressed) {
        this.uPressed = uPressed;
    }

    public boolean getPPressed() {
        return this.pPressed;
    }

    public void setPPressed(boolean pPressed) {
        this.pPressed = pPressed;
    }

    public boolean getIPressed() {
        return this.iPressed;
    }

    public void setIPressed(boolean iPressed) {
        this.iPressed = iPressed;
    }

}
