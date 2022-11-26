package main;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoBar extends JPanel {
    logic.MyFarm myfarm;
    JPanel statsPan;

    public InfoBar() {
        this.setBackground(Color.green);
        statsPan = new JPanel();

        this.myfarm = new logic.MyFarm();

        this.add(statsPan);
        btnOptions();
        showStats();
    }

    public void btnOptions() {
        JButton nextDayBtn = new JButton("Sleep the Night");
        JButton regFarmerBtn = new JButton("Register Farmer");

        nextDayBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myfarm.incrementCurrentDay();
            }
        });

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

    public void showStats() {
        this.myfarm.getFarmer().updateLvl();
        this.statsPan.setLayout(new GridLayout(6, 1));

        mco1.Farmer tempFarmer = this.myfarm.getFarmer();

        statsPan.removeAll();

        statsPan.add(new JLabel("[" + this.myfarm.getFarmName() + "]"));

        statsPan.add(new JLabel(tempFarmer.getName() + " - DAY " + this.myfarm.getCurrentDay()));

        statsPan.add(new JLabel(tempFarmer.getFarmerTitle()));

        statsPan.add(new JLabel("LVL: " + tempFarmer.getLvl()));

        statsPan.add(new JLabel("EXP: " + tempFarmer.getXp() + " / " + (tempFarmer.getLvl() + 1) * 100));

        statsPan.add(new JLabel("BAL: " + tempFarmer.getCoins() + " coins"));

        statsPan.setVisible(true);
        statsPan.revalidate();
        statsPan.repaint();
    }

    public void update() {
        showStats();
        revalidate();
        repaint();
    }
}

class RegisterFarmer extends JDialog {
    final int tileSize = 96;

    public RegisterFarmer(mco1.Farmer farmer) {
        this.pack();
        this.setModal(true);
        this.setAlwaysOnTop(true);
        this.setModalityType(ModalityType.APPLICATION_MODAL);
        this.setLocationRelativeTo(null);
        this.setSize(new Dimension(tileSize * 2, tileSize));
        this.registerFarmer(farmer);
    }

    public void registerFarmer(mco1.Farmer farmer) {
        String[] farmerTier = { "Farmer", "Registered Farmer", "Distinguished Farmer", "Legendary Farmer" };
        int[] tierFees = { 200, 300, 400 }, tierLvl = { 0, 5, 10, 15 };
        int farmerType;

        farmerType = farmer.getFarmerType();
        if (farmerType < 3) { // make sure no out of bounds stuff happens
            if (farmer.getLvl() >= tierLvl[farmerType + 1]) { // check if lvl is sufficient
                JPanel tempP = new JPanel();
                JButton yesBtn = new JButton("Register!");

                yesBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFrame dummy = new JFrame ();
                        dummy.setAlwaysOnTop(true);

                        if (farmer.getCoins() - tierFees[farmerType] >= 5) {
                            farmer.setCoins(farmer.getCoins() - tierFees[farmerType]);
                            farmer.setFarmerType(farmer.getFarmerType() + 1);
                            farmer.setFarmerTitle(farmer.getFarmerType());

                            JOptionPane.showMessageDialog(dummy,
                                    "You are now a " + farmerTier[farmer.getFarmerType()],
                                    "Register Successful!",
                                    JOptionPane.WARNING_MESSAGE);

                        } else {
                            JOptionPane.showMessageDialog(dummy,
                            "Insufficient Coins!",
                            "Register Unsuccessful...",
                            JOptionPane.WARNING_MESSAGE);
                        }

                        dummy.dispose();
                        dispose();
                    }
                });

                tempP.setLayout(new GridLayout(5, 1));

                tempP.add(new JLabel("Current Tier: " + farmerTier[farmerType]));

                tempP.add(new JLabel("Upgrade to " + farmerTier[farmerType + 1]));

                tempP.add(new JLabel("Fee: " + tierFees[farmerType] + " coins"));

                tempP.add(new JLabel("Current Balance: " + farmer.getCoins() + " coins"));

                tempP.add(yesBtn);

                tempP.setVisible(true);

                this.add(tempP);

                this.setVisible(true);
                this.revalidate();
                this.repaint();

                /*** 
                */
            } else
                JOptionPane.showMessageDialog(this,
                        "Insufficient level to register higher tier. Required Lvl: " + tierLvl[farmerType + 1],
                        "Register Unsuccessful...",
                        JOptionPane.WARNING_MESSAGE);
        } else
            JOptionPane.showMessageDialog(this,
                    "Maximum Farmer Tier reached!" + tierLvl[farmerType + 1],
                    "Register Unsuccessful...",
                    JOptionPane.WARNING_MESSAGE);
    }
    
}
