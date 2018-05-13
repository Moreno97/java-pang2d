package pang2d;

import mapanel.Mapanel;

import javax.swing.*;

public class Space extends JFrame {
    Mapanel p;

    public Space(){
        this.setBounds(0,0,853,571);
        this.setTitle("Pang Vrs. DAM");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        p=new Mapanel();
        this.getContentPane().add(p);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
