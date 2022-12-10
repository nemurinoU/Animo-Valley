package logic;

import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.Box;

public class NamePrompt extends JOptionPane {
    JTextField farmerNameField;
    JTextField farmNameField;
    JPanel promptPanel;

    String farmerName, farmName;

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

    public void askName () {
        String options[] = {"OK"};
        int result;

        do {
            result = JOptionPane.showOptionDialog (null, promptPanel, "Who are you?", JOptionPane.NO_OPTION, 
            JOptionPane.QUESTION_MESSAGE, null, options , options[0]);

            if (result == JOptionPane.CLOSED_OPTION) System.exit(0);

        } while (this.farmerNameField.getText().isEmpty() || this.farmNameField.getText().isEmpty());
    }

    public String getFarmName () {
        return this.farmNameField.getText();
    }

    public String getFarmerName () {
        return this.farmerNameField.getText();
    }
}
