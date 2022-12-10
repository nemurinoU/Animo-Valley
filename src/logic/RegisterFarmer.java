package logic;

import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/***
 * <p>
 * This class allows the user to register the farmer.
 * </p>
 *
 * @author  Francis Martinez, Richard Pecson Jr.
 * @version a0.0.8
 * @since   2022-12-10
 */
public class RegisterFarmer extends JDialog {
    final int tileSize = 96;

    /**
     * This constructor allows the creation of the RegisterFarmer object
     * <p>
     * The farmer parameter is called because the statistics of the farmer will be modified using the registerFarmer() method
     * @param farmer The farmer object
     */
    public RegisterFarmer(logic.Farmer farmer) {
        this.pack();
        this.setModal(true);
        this.setAlwaysOnTop(true);
        this.setModalityType(ModalityType.APPLICATION_MODAL);
        this.setLocationRelativeTo(null);
        this.setSize(new Dimension(tileSize * 2, tileSize));
        this.registerFarmer(farmer);
    }

    
    /** 
     * This method deals with the farmer registration
     * @param farmer It accepts a farmer or the player
     */
    public void registerFarmer(logic.Farmer farmer) {
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

                /**
                 * Creating the layout for the register farmer pop up
                 */
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
        }
    }
    
}