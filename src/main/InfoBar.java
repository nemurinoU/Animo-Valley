package main;

import java.awt.*;
import javax.swing.*;

public class InfoBar extends JPanel {
    final int tileSize = 96;
    logic.MyFarm myfarm;

    public InfoBar () {
        this.setBackground(Color.gray);
        //this.setPreferredSize(new Dimension (tileSize, tileSize));

        this.myfarm = new logic.MyFarm();
        showStats ();
    }

    public void showStats () {
        this.removeAll();

        JPanel tempPan = new JPanel();
        mco1.Farmer tempFarmer = this.myfarm.getFarmer();
        tempPan.setLayout (new GridLayout (6, 1));
        
        tempPan.add (new JLabel ("[" + this.myfarm.getFarmName() + "]"));

        tempPan.add (new JLabel (tempFarmer.getName() + " - DAY " + this.myfarm.getCurrentDay()));

        tempPan.add (new JLabel (tempFarmer.getFarmerTitle()));

        tempPan.add (new JLabel ("LVL: " + tempFarmer.getLvl()));
        
        tempPan.add (new JLabel ("EXP: " + tempFarmer.getXp() + " / " + (tempFarmer.getLvl() + 1) * 100));

        tempPan.add (new JLabel ("BAL: " + tempFarmer.getCoins () + " coins"));

        this.add (tempPan);
        this.setVisible(true);
    }

    public void update () {
        showStats ();
        revalidate ();
        repaint ();
    }
}
