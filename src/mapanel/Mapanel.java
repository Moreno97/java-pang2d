package mapanel;

import controller.InputHandler;
import gfx.SpriteSheetHandler;
import sprites.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Mapanel extends JPanel implements ActionListener {

    private Image imgFondo;
    private URL urlFondo;
    private JButton jbStart;
    private Integer level;
    private Player player;

    public Mapanel() {
        preInit();
        initComponents();
        player = new Player(this);
        addKeyListener(new InputHandler(player, this));
    }

    private void preInit() {
        urlFondo = this.getClass().getResource("images/PantIni.png");
        imgFondo = new ImageIcon(urlFondo).getImage();
    }

    public void paint(Graphics g) {
        g.drawImage(imgFondo, 0, 0, getWidth(), getHeight(), this);
        setOpaque(false);
        setFocusable(true);
        super.paintComponent(g);
    }

    private void initComponents() {
        this.setLayout(null);
        jbStart = new JButton("PLAY");
        jbStart.setBounds(382, 355, 90, 20);
        jbStart.addActionListener(this);
        this.add(jbStart);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.remove(jbStart);
        mapLvs(e.getActionCommand());
    }

    private void mapLvs(String lvl) {
        switch (lvl) {
            case "PLAY":
                urlFondo = this.getClass().getResource("images/lvl1.png");
                imgFondo = new ImageIcon(urlFondo).getImage();
                player.drawCharacter((Graphics2D) getGraphics());
                break;
            case "lv2":
                urlFondo = this.getClass().getResource("images/lvl2.png");
                imgFondo = new ImageIcon(urlFondo).getImage();
                this.repaint();
                break;
            case "lv3":
                urlFondo = this.getClass().getResource("images/lvl3.png");
                imgFondo = new ImageIcon(urlFondo).getImage();
                this.repaint();
                break;
            case "lv4":
                urlFondo = this.getClass().getResource("images/lvl4.png");
                imgFondo = new ImageIcon(urlFondo).getImage();
                this.repaint();
                break;

        }

    }
}
