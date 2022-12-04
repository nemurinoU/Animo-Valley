package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean spacePressed, uPressed, pPressed, iPressed;
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
    }
}
