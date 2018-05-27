package pang2d;

import mapanel.Mapcanvas;

import javax.swing.*;
import java.awt.*;

public class Space extends JFrame {

    Mapcanvas mpc;

    public Space() {
        Container panel;
        setBounds(0, 0, 850, 570);
        setTitle("Pang Vrs. DAM");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout());

        panel = getContentPane();
        mpc = new Mapcanvas();
        panel.add(mpc);
        setVisible(true);
        new Thread(mpc).start();
    }
}
