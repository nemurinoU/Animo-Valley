package logic;

import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Box;
/***
 * <p>
 * This class is used to create the name prompt shown at the beginning of the game.
 * </p>
 *
 * @author  Francis Martinez, Richard Pecson Jr.
 * @version a0.0.8
 * @since   2022-12-10
 */
public class NamePrompt extends JOptionPane {
    JTextField farmerNameField;
    JTextField farmNameField;
    JPanel promptPanel;

    String farmerName, farmName;

    /**
     * This constructor sets the default values of the name prompt
     */
    public NamePrompt () {
        this.farmerNameField = new JTextField (15);
        this.farmNameField = new JTextField (20);
        this.promptPanel = new JPanel ();

        this.promptPanel.add (new JLabel("Farmer Name: "));
        this.promptPanel.add (this.farmerNameField);

        this.promptPanel.add(Box.createHorizontalStrut(15));
        this.promptPanel.add (new JLabel ("Farm Name: "));
        this.promptPanel.add (this.farmNameField);
        
        //this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.askName();
        // remove the escape x button
        // dont let the user enter empty string
    }

    /**
     * This method asks for the name of the farmer
     */
    public void askName () {
        String options[] = {"OK"};
        int result;

        do {
            result = JOptionPane.showOptionDialog (null, promptPanel, "Who are you?", JOptionPane.NO_OPTION, 
            JOptionPane.QUESTION_MESSAGE, null, options , options[0]);

            if (result == JOptionPane.CLOSED_OPTION) System.exit(0);

        } while (this.farmerNameField.getText().isEmpty() || this.farmNameField.getText().isEmpty());
    }

    
    /** 
     * This method gets the farm name
     * @return String the farm's name
     */
    public String getFarmName () {
        return this.farmNameField.getText();
    }

    
    /**
     * This method gets the farmer name 
     * @return String the farmer's name
     */
    public String getFarmerName () {
        return this.farmerNameField.getText();
    }
}
