package main;

import java.awt.*;

import javax.imageio.ImageIO;

import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import logic.RegisterFarmer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/***
 * <p>
 * This class is used to display the InfoBar shown above the main Game Board.
 * </p>
 *
 * @author  Francis Martinez, Richard Pecson Jr.
 * @version a0.0.8
 * @since   2022-11-28
 */
public class InfoBar extends JPanel {
    /**
     * myFarm is the current farm instance
     */
    private logic.MyFarm myfarm;
    /**
     * statsPan is the panel for the statistics
     * feedBack is the panel for the feedback (center panel)
     */
    private JPanel statsPan, feedBack;
    /** regFarmerBtn is the button to register the farmer */
    private JButton regFarmerBtn;
    /**
     * pickImg is the image of pickaxe
     * fertilizerImg is the image of the fertilizer bag
     * shovelImg is the image of the shovel
     * hoeImg is the image of the hoe
     * wateringCanImg is the image of the watering can
     * seedBagImg is the image of the seed bag
     */
    private BufferedImage pickImg, fertilizerImg, shovelImg, hoeImg, wateringCanImg, seedBagImg;
    /**
     * toolPan is the panel for the toolbar (bottom of game)
     */
    private JPanel toolPan;

    /**
     * This is the constructor that accepts the farmName and farmerName as parameters for the top InfoBar
     * @param farmName The farm's name
     * @param farmerName The farmer's name
     */
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

    /**
     * This constructor is used for the InfoBar below the main game board.
     * <p>
     * It accepts no parameters.
     */
    public InfoBar(){
        this.setBackground(Color.decode("#B59F84"));

        this.toolPan = new JPanel();
        
        this.add(toolPan);

        showToolCommands();
    }

    
    /** 
     * This method gets the game world or MyFarm class
     * @return MyFarm The game world instance
     */
    public logic.MyFarm getMyFarm () {
        return this.myfarm;
    }

    
    /** 
     * This method is used to get the button.
     * @return JButton The "Register Farmer" button
     */
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

    /**
     * 
     */
    public void showFeedback() {
        this.feedBack.setLayout(new GridLayout(2, 1));

        this.feedBack.add(new JLabel ("Current Seed: Turnip (5.0 coins)"));
        this.feedBack.add(new JLabel ("Welcome to Animo Valley!"));

        feedBack.setVisible(true);
        feedBack.revalidate();
        feedBack.repaint();
    }

    
    /** 
     * @param seed
     * @param msg
     */
    public void updateFeedback (String seed, String msg) {
        this.feedBack.removeAll();

        this.feedBack.add(new JLabel ("Current Seed: " + seed));
        this.feedBack.add(new JLabel (msg));

        feedBack.setVisible(true);
        feedBack.revalidate();
        feedBack.repaint();
    }

    
    /** 
     * @param seed
     */
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
        this.toolPan.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
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

        // Hoe
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        toolPan.add(new JLabel(new ImageIcon(hoeImg)), c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        toolPan.add(new JLabel("   [1]"), c);

        //Watering Can
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 0;
        toolPan.add(new JLabel(new ImageIcon(wateringCanImg)), c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 1;
        toolPan.add(new JLabel("   [2]"), c);

        //Fertilizer
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 0;
        toolPan.add(new JLabel(new ImageIcon(fertilizerImg)), c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 1;
        toolPan.add(new JLabel("   [3]"), c);

        //Pickaxe
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 4;
        c.gridy = 0;
        toolPan.add(new JLabel(new ImageIcon(pickImg)), c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 4;
        c.gridy = 1;
        toolPan.add(new JLabel("  [4]"), c);
        

        //Shovel
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 5;
        c.gridy = 0;
        toolPan.add(new JLabel(new ImageIcon(shovelImg)), c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 5;
        c.gridy = 1;
        toolPan.add(new JLabel("  [5]"), c);

        //Seeds
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 6;
        c.gridy = 0;
        toolPan.add(new JLabel(new ImageIcon(seedBagImg)), c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 6;
        c.gridy = 1;
        toolPan.add(new JLabel("   [0]"), c);
        //Seed commands
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 7;
        c.gridy = 0;
        toolPan.add(new JLabel("   [-] Select previous seed   "), c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 7;
        c.gridy = 1;
        toolPan.add(new JLabel("   [+] Select next seed"), c);
    }

    public void update() {
        showStats();
        revalidate();
        repaint();
    }
}
