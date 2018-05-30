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
    private int dx, dy, x, y;
    private Mapcanvas game;
    private ImageIcon character;

    public Player(int dx, int dy, int x, int y, Mapcanvas game) {
        super(dx, dy, x, y);
        this.game = game;
        this.life = 3;
        this.dx = dx;
        this.dy = dy;
        this.x = x;
        this.y = y;
        this.character = new ImageIcon(new SpriteSheetHandler("res/sprites.png").crop(3, 1, 47, 49));
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void drawCharacter(Graphics2D gr2D) {
        gr2D.drawImage(character.getImage(), getDx(), getDy(), getX(), getY(), null);
    }

    public void toRight() {
        this.dx = dx + 60;
        for (int i = 4; i >= 0; i--) {
            this.character = new ImageIcon(new SpriteSheetHandler("res/sprites.png").crop(3, 1, 47, 49));
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void toLeft() {
        this.dx = dx - 60;
        for (int i = 4; i >= 0; i--) {
            this.character = new ImageIcon(new SpriteSheetHandler("res/sprites.png").crop(3, 1, 47, 49));
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void changeCharacter() {
        //this.dx = dx - 60;
        this.character = new ImageIcon(new SpriteSheetHandler("res/sprites.png").crop(4, 1, 47, 49));
        try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
            e.printStackTrace();
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