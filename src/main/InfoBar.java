package main;

import java.awt.*;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import logic.RegisterFarmer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoBar extends JPanel {
    private logic.MyFarm myfarm;
    private JPanel statsPan, feedBack;
    private JButton regFarmerBtn;
    //Toolbar stuff below
    private BufferedImage pickImg, fertilizerImg, shovelImg, hoeImg, wateringCanImg, seedBagImg;
    private JPanel toolPan;

    public InfoBar(String farmName, String farmerName) {
        Border compound, raisedbevel, loweredbevel;
        Border line = BorderFactory.createLineBorder(Color.decode("#d29226"));

        raisedbevel = BorderFactory.createRaisedBevelBorder();
        loweredbevel = BorderFactory.createLoweredBevelBorder();
        compound = BorderFactory.createCompoundBorder(
                          raisedbevel, loweredbevel);

        compound = BorderFactory.createCompoundBorder(
                            line, compound);

        compound = BorderFactory.createTitledBorder(
                          compound, "Animo Valley",
                          TitledBorder.RIGHT,
                          TitledBorder.ABOVE_TOP);
        
        this.setBackground(Color.decode("#f1ab27"));
        this.setBorder(compound);
        this.regFarmerBtn = new JButton("Register Farmer");
        this.statsPan = new JPanel();
        this.feedBack = new JPanel();

        this.myfarm = new logic.MyFarm(farmName, farmerName);

        this.add(statsPan);
        this.add(feedBack);

        btnOptions();
        showStats();
        showFeedback();
    }

    public InfoBar(){
        this.setBackground(Color.decode("#B59F84"));

        this.toolPan = new JPanel();
        
        this.add(toolPan);

        showToolCommands();
    }

    public logic.MyFarm getMyFarm () {
        return this.myfarm;
    }

    public JButton getFarmerBtn () {
        return this.regFarmerBtn;
    }

    public void btnOptions() {
        JButton nextDayBtn = new JButton("Sleep the Night");
        //Sleep the night button
        nextDayBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myfarm.incrementCurrentDay();

                // this function grows the crops in field
                myfarm.getFarmField().update(myfarm.getCurrentDay());
            }
        });

        //Register Farmer button
        regFarmerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // reg farmer logic
                RegisterFarmer rf = new RegisterFarmer(myfarm.getFarmer());
            }
        });

        JPanel btnPan = new JPanel();
        btnPan.setLayout(new GridLayout(2, 1));

        btnPan.add(nextDayBtn);
        btnPan.add(regFarmerBtn);

        this.add(btnPan);
    }

    public void showFeedback() {
        this.feedBack.setLayout(new GridLayout(2, 1));

        this.feedBack.add(new JLabel ("Current Seed: Turnip (5.0 coins)"));
        this.feedBack.add(new JLabel ("Welcome to Animo Valley!"));

        feedBack.setVisible(true);
        feedBack.revalidate();
        feedBack.repaint();
    }

    public void updateFeedback (String seed, String msg) {
        this.feedBack.removeAll();

        this.feedBack.add(new JLabel ("Current Seed: " + seed));
        this.feedBack.add(new JLabel (msg));

        feedBack.setVisible(true);
        feedBack.revalidate();
        feedBack.repaint();
    }

    public void updateFeedback (String seed) {
        Component c = this.feedBack.getComponent(0);

        if (c instanceof JLabel) ((JLabel)c).setText("Current Seed: " + seed);


        feedBack.setVisible(true);
        feedBack.revalidate();
        feedBack.repaint();
    }

    public void showStats() {
        this.myfarm.getFarmer().updateLvl();
        this.statsPan.setLayout(new GridLayout(6, 1));

        logic.Farmer tempFarmer = this.myfarm.getFarmer();
        if (tempFarmer.getFarmerType() == 3) regFarmerBtn.setEnabled(false);

        statsPan.removeAll();

        statsPan.add(new JLabel("[" + this.myfarm.getFarmName() + "]"));

        statsPan.add(new JLabel(tempFarmer.getName() + " - DAY " + this.myfarm.getCurrentDay()));

        statsPan.add(new JLabel(tempFarmer.getFarmerTitle()));

        statsPan.add(new JLabel("LVL: " + tempFarmer.getLvl()));

        statsPan.add(new JLabel("EXP: " + tempFarmer.getXp() + " / " + (tempFarmer.getLvl() + 1) * 100));

        //statsPan.add(new JLabel("BAL: " + tempFarmer.getCoins() + " coins"));
        statsPan.add(new JLabel(String.format("BAL %.2f coins", tempFarmer.getCoins())));

        statsPan.setVisible(true);
        statsPan.revalidate();
        statsPan.repaint();
    }

    public void showToolCommands(){
        this.toolPan.setLayout(new GridLayout(7, 2));
        //getIcons();
        try {
            pickImg = ImageIO.read(getClass().getResourceAsStream("res/Pick.png"));
            shovelImg = ImageIO.read(getClass().getResourceAsStream("res/Shovel.png"));
            hoeImg = ImageIO.read(getClass().getResourceAsStream("res/Hoe.png"));
            wateringCanImg = ImageIO.read(getClass().getResourceAsStream("res/Watering_Can.png"));
            seedBagImg = ImageIO.read(getClass().getResourceAsStream("res/Seed_Bag.png"));
            fertilizerImg = ImageIO.read(getClass().getResourceAsStream("res/Fertilizer2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //JLabel pick = );
        toolPan.add(new JLabel(new ImageIcon(hoeImg)));
        toolPan.add(new JLabel("[1] Hoe "));
        toolPan.add(new JLabel(new ImageIcon(wateringCanImg)));
        toolPan.add(new JLabel("[2] Watering Can    "));
        toolPan.add(new JLabel(new ImageIcon(fertilizerImg)));
        toolPan.add(new JLabel("[3] Fertilizer  "));
        toolPan.add(new JLabel(new ImageIcon(pickImg)));
        toolPan.add(new JLabel("[4] Pickaxe "));
        toolPan.add(new JLabel(new ImageIcon(shovelImg)));
        toolPan.add(new JLabel("[5] Shovel  "));
        toolPan.add(new JLabel(new ImageIcon(seedBagImg)));
        toolPan.add(new JLabel("[0] Seed Bag  "));
        toolPan.add(new JLabel("   [-] Select previous seed   "));
        toolPan.add(new JLabel("[+] Select next seed"));

        //JLabel
        //toolPan.add(new JLabel("test"));
    }

    public void update() {
        showStats();
        revalidate();
        repaint();
        //getIcons();
    }
}
