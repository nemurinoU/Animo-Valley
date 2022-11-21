import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.BorderFactory;
import java.util.ArrayList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;


public class GUI {
    private JFrame mainFrame;
    private JPanel tile;
    private JPanel stats;

    public GUI () {
            this.mainFrame = new JFrame("Animo Valley");

            this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.mainFrame.setLayout(new FlowLayout());
            this.mainFrame.setSize(800, 800);
            this.mainFrame.setLocationRelativeTo(null);
    }

    // bridges the plotGrid arraylist and graphics
    public void initializePlotTiles () {
            GridLayout layout = new GridLayout(10, 5);
            Border border = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black), new EmptyBorder(10, 10, 10, 10));
            JLabel tempLbl;
            layout.setHgap (20);
            this.mainFrame.setSize(400, 800);

            this.tile = new JPanel ();
            this.tile.setLayout (layout);
            
            for (int i = 0; i < 10; i++) 
                    for (int j = 0; j < 5; j++) {
                        tempLbl = new JLabel("(" + i + ", " + j + ")");
                        tempLbl.setBorder (border);
                        this.tile.add(tempLbl);
                    }
                        
                        
            
            this.mainFrame.add (this.tile);
    }

    public void initializeNamePrompt (Farmer farmer) {
        JLabel namePromptLbl = new JLabel();
        JLabel farmNamePromptLbl = new JLabel();

        namePromptLbl.setText("Farmer Name: ");
        farmNamePromptLbl.setText("Farm Name: ");

        JTextField farmerNameTf = new JTextField();
        JTextField farmNameTf = new JTextField();
        farmerNameTf.setColumns(10);
        farmNameTf.setColumns(10);

        JButton farmBtn = new JButton("Submit");
        farmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!farmerNameTf.getText().equals("") && !farmNameTf.getText().equals("")) {
                        farmer.setName (farmerNameTf.getText());
                        farmer.setFarmName (farmNameTf.getText());

                        mainFrame.remove(namePromptLbl);
                        mainFrame.remove(farmNamePromptLbl);
                        mainFrame.remove(farmNameTf);
                        mainFrame.remove(farmerNameTf);
                        mainFrame.remove(farmBtn);

                        reloadFrame ();
                }
            }
        });

        this.mainFrame.setSize(400, 200);
        this.mainFrame.setLocationRelativeTo(null);
        
        this.mainFrame.add(namePromptLbl);
        this.mainFrame.add(farmerNameTf);
        this.mainFrame.add(farmNamePromptLbl);
        this.mainFrame.add(farmNameTf);
        this.mainFrame.add(farmBtn);
        this.mainFrame.setVisible(true);
    }

    public void initializeDisplayStats (Farmer farmer, int currentDay) {
        GridLayout layout = new GridLayout(5, 1);
        Border border = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black), new EmptyBorder(10, 10, 10, 10));
        this.stats = new JPanel ();

        this.stats.setBorder (border);
        this.stats.setLayout (layout);

        this.stats.add(new JLabel ("[ " + farmer.getFarmName() + "]"));
        this.stats.add(new JLabel (farmer.getName() + " - DAY " + currentDay));
        this.stats.add(new JLabel ("LVL: " + farmer.getLvl()));
        this.stats.add(new JLabel ("EXP: " + farmer.getXp() + " / " + (farmer.getLvl() + 1) * 100));
        this.stats.add(new JLabel (String.format("BAL: %.2f%s%n", farmer.getCoins() , " coins")));

        this.mainFrame.add (this.stats);
    }

    public void updateDisplayStats (Farmer farmer, int currentDay) {
        this.stats.setVisible(false);
        this.stats.removeAll();

        this.stats.add(new JLabel ("[ " + farmer.getFarmName() + "]"));
        this.stats.add(new JLabel (farmer.getName() + " - DAY " + currentDay));
        this.stats.add(new JLabel ("LVL: " + farmer.getLvl()));
        this.stats.add(new JLabel ("EXP: " + farmer.getXp() + " / " + (farmer.getLvl() + 1) * 100));
        this.stats.add(new JLabel (String.format("BAL: %.2f%s%n", farmer.getCoins() , " coins")));
        this.stats.setVisible(true);
    }

    public void initializeButtons () {
        JButton farmBtn = new JButton("Sleep");
        farmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    
    public void reloadFrame () {
        this.mainFrame.invalidate();
        this.mainFrame.validate();
        this.mainFrame.repaint();
    }

    public JFrame getFrame () {
            return this.mainFrame;
    }
}