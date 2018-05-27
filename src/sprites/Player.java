package sprites;

import gfx.SpriteSheetHandler;
import mapanel.Collision;
import mapanel.Mapcanvas;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Antonio Moreno Valls
 */
public class Player extends Sprite {

    private int life;
    private Mapcanvas game;
    private ImageIcon character;

    public Player(int dx, int dy, int x, int y, Mapcanvas game) {
        super(dx, dy, x, y);
        this.game = game;
        this.life = 3;

        this.character = new ImageIcon(new SpriteSheetHandler("res/d814p9r.png").crop(0, 3, 66, 66));
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }


    public void drawCharacter(Graphics2D gr2D) {
        gr2D.drawImage(character.getImage(), getDx(), getDy(), getX(), getY(), null);
    }

    public void toRight() {
        super.setDx(super.getDx()+60);
        for (int i = 3; i >= 2; i--) {
            this.character = new ImageIcon(new SpriteSheetHandler("res/d814p9r.png").crop(i, 3, 66, 66));
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void toLeft() {
        super.setDx(super.getDx()-60);
        for (int i = 3; i >= 2; i--) {
            this.character = new ImageIcon(new SpriteSheetHandler("res/d814p9r.png").crop(i, 3, 66, 66));
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                Collision.checkPlayer2WallCollision(this, game);
                Thread.sleep(80);
            }
        } catch (InterruptedException e) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
