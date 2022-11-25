package main;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Dimension;

public class Driver {
    
    public static void main(String[] args){
        JFrame window = new JFrame();
        GridBagConstraints gbc = new GridBagConstraints();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new GridBagLayout());
        window.setResizable(true);
        window.setTitle("Animo Valley");

        Board board = new Board();
        InfoBar menu = new InfoBar();
        /***
         * 
         * Note: What this does is turn the window into a GridBag layout, which is like a grid layout but the cells
         * can be resized to our liking. Will clean this up later. Cannot turn into a subroutine because java
         * passes by VALUE not REFERENCE.
         */
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        window.add (menu, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        window.add(board, gbc);
        

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        board.startGameThread();
    }
}