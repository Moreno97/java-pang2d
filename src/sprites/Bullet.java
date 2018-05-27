package sprites;

import gfx.SpriteSheetHandler;
import mapanel.Collision;
import mapanel.Mapcanvas;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Antonio Moreno Valls
 **/
public class Bullet extends Thread {
    private float dx, dy, speedX, speedY, radio, mass;
    private final Stack<Bullet> bulletStack;
    private boolean isCollided;
    private ImageIcon bullet;
    private Mapcanvas mapcanvas;

    public Bullet(float dx, float dy, float speedX, float speedY, float radio, float mass, Mapcanvas mapcanvas, Stack<Bullet> bulletStack) {
        this.dx = dx;
        this.dy = dy;
        this.speedX = speedX;
        this.speedY = speedY;
        this.radio = radio;
        this.mass = mass;
        this.mapcanvas = mapcanvas;
        this.bulletStack = bulletStack;
        this.bullet = new ImageIcon(new SpriteSheetHandler("res/clash2.png")
                .getImageWithoutCropping());
    }

    public float getDx() {
        return dx;
    }

    public float getDy() {
        return dy;
    }

    public float getSpeedX() {
        return speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public float getRadio() {
        return radio;
    }

    public float getMass() {
        return mass;
    }

    public void setDx(float dx) {
        this.dx = dx;
    }

    public void setDy(float dy) {
        this.dy = dy;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public void setRadio(float radio) {
        this.radio = radio;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public synchronized void paint(Graphics2D gr2D) {
        gr2D.drawImage(this.bullet.getImage(), (int) getDx(), (int) getDy(), null);

        if (isCollided) {
            gr2D.drawImage(new ImageIcon(new SpriteSheetHandler("res/explosion03.png").crop(3, 0, 128, 128)).getImage()
                    , (int) getDx() - 45, (int) getDy() - 35, null);
        }
    }

    private void restDy() {
        // TODO: Set physics to reduce bullet speed
        this.dy -= this.speedY;
    }

    public void setIsCollided(boolean isCollided) {
        this.isCollided = isCollided;
    }

    @Override
    public void run() {
        while (true) {
            Collision.checkBall2WallCollision(this, this.mapcanvas);
            restDy();
            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                Logger.getLogger(Bullet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void remove() {
        synchronized (bulletStack) {
            if (!bulletStack.isEmpty()) {
                if (isCollided) {
                    bulletStack.remove(this);
                }
            }
        }
    }

}
