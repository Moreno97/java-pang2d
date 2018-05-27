package pang2d;

import mapanel.Mapcanvas;

import javax.swing.*;
import java.awt.*;

public class Space extends JFrame {

    Mapcanvas mpc;


    public Space(){
        mpc=new Mapcanvas();
        initMap();

    }

    private void initMap(){
        Container panel;
        setBounds(0,0,853,680);
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
