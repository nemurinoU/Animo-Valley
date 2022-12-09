package main;

import java.awt.*;
import javax.swing.*;

import logic.RegisterFarmer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoBar extends JPanel {
    private logic.MyFarm myfarm;
    private JPanel statsPan, feedBack;
    private JButton regFarmerBtn;

    public InfoBar(String farmName, String farmerName) {
        this.setBackground(Color.green);
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

    public logic.MyFarm getMyFarm () {
        return this.myfarm;
    }

    public JButton getFarmerBtn () {
        return this.regFarmerBtn;
    }

    public void btnOptions() {
        JButton nextDayBtn = new JButton("Sleep the Night");

        nextDayBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myfarm.incrementCurrentDay();

                // this function grows the crops in field
                myfarm.getFarmField().update(myfarm.getCurrentDay());
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

    public void update() {
        showStats();
        revalidate();
        repaint();
    }
}
