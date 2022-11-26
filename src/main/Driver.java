package main;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class Driver {
    
    public static void main(String[] args){
        JFrame window = new JFrame();
        GridBagConstraints gbc = new GridBagConstraints();

        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new GridBagLayout());
        window.setResizable(true);
        window.setTitle("Animo Valley");
        
        Board board = new Board();
        /***
         * 
         * Note: What this does is turn the window into a GridBag layout, which is like a grid layout but the cells
         * can be resized to our liking. Will clean this up later. Cannot turn into a subroutine because java
         * passes by VALUE not REFERENCE.
         */
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        window.add (board.getMenu (), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        window.add(board, gbc);
        

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        board.startGameThread();
        window.setVisible(true);
    }
}