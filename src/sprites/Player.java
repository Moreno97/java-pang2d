package sprites;

import mapanel.Mapanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Player implements Runnable, ActionListener {

    // spritesMap HashMap
    // life int
    // weapon: Bubble
    // positionX: int

    private int life;
    private int dx;
    private Mapanel mapanel;
    private ImageIcon character;

    public Player(Mapanel mapanel) {
        this.mapanel = mapanel;
        this.life = 3;
        this.dx = mapanel.getWidth() / 2;
        this.character = new ImageIcon(getClass().getResource("resources/right.png"));
    }

    public void drawCharacter(Graphics2D gr2D) {
        gr2D.drawImage(this.getCharacter().getImage(), 100, 100, 100, 100, null);
    }

    public void moveLeft() {
        setCharacter(new ImageIcon(getClass().getResource("resources/left.png")));
        this.dx = dx - 20;
    }

    public void moveRight() {
        setCharacter(new ImageIcon(getClass().getResource("resources/right.png")));
        this.dx = dx + 20;
    }

//    public void moveUp() {
//        setCharacter(new ImageIcon(getClass().getResource("/resources/up-look.png")));
//        this.dy = dy - 20;
//    }
//
//    public void moveDown() {
//        setCharacter(new ImageIcon(getClass().getResource("/resources/down-look.png")));
//        this.dy = dy + 20;
//    }

//    public void jump()  {
//        int i = 0;
//        int z = this.dy;
//
//        while (i <= 8) {
//            this.dy = z + 3 * i * i - 24 * i;
//            try {
//                Thread.sleep(30);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(CharacterGame.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            i = i + 1;
//        }
//    }

    public ImageIcon getCharacter() {
        return character;
    }

    public void setCharacter(ImageIcon character) {
        this.character = character;
    }

    @Override
    public void run() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
