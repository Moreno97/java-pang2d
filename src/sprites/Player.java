package sprites;

import controller.InputHandler;
import gfx.SpriteSheetHandler;
import jdk.internal.util.xml.impl.Input;
import mapanel.Mapcanvas;

import javax.swing.*;
import java.awt.*;

public class Player extends Thread {

    // life int
    // weapon: Bubble

    private int life;
    private int dx;
    private Mapcanvas mapanel;
    private ImageIcon character;
    private InputHandler inputHandler;

    public Player(Mapcanvas mapanel, InputHandler inputHandler) {
        this.mapanel = mapanel;
        this.life = 3;
        this.dx = mapanel.getWidth() / 2;
        this.character = new ImageIcon(new SpriteSheetHandler("res/d814p9r.png").crop(0, 2, 66, 66));
    }

    public void drawCharacter(Graphics2D gr2D) {
        gr2D.drawImage(character.getImage(), dx - 20, mapanel.getBounds().height - 65, 66, 66, null);
    }

    public void toRight() {
        this.dx = dx + 20;
        this.character = new ImageIcon(new SpriteSheetHandler("res/d814p9r.png").crop(1, 2, 66, 66));

        try {
            Thread.sleep(120);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.character = new ImageIcon(new SpriteSheetHandler("res/d814p9r.png").crop(0, 2, 66, 66));

    }

    public void toLeft() {
        this.dx = dx - 20;
        this.character = new ImageIcon(new SpriteSheetHandler("res/d814p9r.png").crop(1, 1, 66, 66));

        try {
            Thread.sleep(120);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.character = new ImageIcon(new SpriteSheetHandler("res/d814p9r.png").crop(0, 1, 66, 66));
    }

    @Override
    public void run() {
        // TODO: Implement collision with map and objects on run method
    }

}
