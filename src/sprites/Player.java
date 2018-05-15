package sprites;

import gfx.SpriteSheetHandler;
import mapanel.Mapanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Player {

    // life int
    // weapon: Bubble

    private int life;
    private int dx;
    private Mapanel mapanel;
    private ImageIcon character;

    public Player(Mapanel mapanel) {
        this.mapanel = mapanel;
        this.life = 3;
        this.dx = mapanel.getWidth() / 2;
        this.character = new ImageIcon(new SpriteSheetHandler("res/player16x16.png").crop(1, 0, 16, 16));
    }

    public void drawCharacter(Graphics2D gr2D) {
        gr2D.drawImage(character.getImage(), dx - 20, mapanel.getBounds().height - 50, 48, 48, null);
    }

    public void toRight(Graphics2D gr2D) {
        this.dx = dx + 20;
        this.character = new ImageIcon(new SpriteSheetHandler("res/player16x16.png").crop(1, 0, 16, 16));
        gr2D.drawImage(character.getImage(), dx, mapanel.getBounds().height - 50, 48, 48, null);
    }
}
