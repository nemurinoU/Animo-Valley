package main;

import java.awt.*;
import javax.swing.*;

public class InfoBar extends JPanel {
    final int tileSize = 48;
    logic.MyFarm myfarm;

    public InfoBar () {
        this.setBackground(Color.gray);
        this.setPreferredSize(new Dimension (tileSize, tileSize));
        this.add(new JLabel("TEST!"));

        this.myfarm = new logic.MyFarm();
    }
}
