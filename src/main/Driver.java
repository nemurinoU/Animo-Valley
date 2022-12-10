package main;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JOptionPane;

/***
 * <p>
 * This class is the driver class containing the main method.
 * </p>
 *
 * @author  Francis Martinez, Richard Pecson Jr.
 * @version a0.0.8
 * @since   2022-11-28
 */
public class Driver {
    
    /**
     * The main method
     * @param args Command line arguments
     */
    public static void main(String[] args){
        boolean exitCode = false, retry = true;
        JFrame window = new JFrame();
        Board board = new Board();
        
        while (!exitCode) {
            if (board.getMenu().getMyFarm().gameOver()) {
                exitCode = true;

            }
            else if (retry) {
                retry = false;
                
                GridBagConstraints gbc = new GridBagConstraints();

                window.pack();
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.setLayout(new GridBagLayout());
                window.setResizable(false);
                window.setTitle("Animo Valley");
                
                
                /***
                 * 
                 * Note: What this does is turn the window into a GridBag layout, which is like a grid layout but the cells
                 * can be resized to our liking. Will clean this up later. 
                 */
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.fill = GridBagConstraints.HORIZONTAL;

                window.add (board.getMenu (), gbc);

                gbc.gridx = 0;
                gbc.gridy = 1;
                window.add(board, gbc);
                
                gbc.gridx = 0;
                gbc.gridy = 2;
                window.add(board.getToolbar(), gbc);
                
                window.pack();

                window.setLocationRelativeTo(null);
                window.setVisible(true);

                board.startGameThread();
                window.setVisible(true);
            }

            if (exitCode) {
                // ask user if they want to retry or exit
                System.out.println ("helloasdas?");

                int result = JOptionPane.showConfirmDialog(window, "You ran out of money and have no active growing crops. Retry?", 
                "GAME OVER!!",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.ERROR_MESSAGE);

                if(result == JOptionPane.YES_OPTION) {
                    retry = true;
                    exitCode = false;

                    window.setVisible(false);
                    window = null;

                    board.setVisible(false);
                    board = null;

                    window = new JFrame();
                    board = new Board();
                }
                else if (result == JOptionPane.NO_OPTION){
                    // do nothing
                    // exit game
                }
                else {
                    // do nothing
                    // exit game
                }
            }
        }

        System.out.println ("GAME OVER!! CLOSING THE GAME.");
        System.exit(0);
    }
}