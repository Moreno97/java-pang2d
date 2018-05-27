package sprites;

import gfx.SpriteSheetHandler;
import mapanel.Collision;
import mapanel.Mapcanvas;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

import static pang2d.Utils.playSound;

/**
 * Created by Antonio Moreno Valls
 **/
public class Bullet extends Thread {
    private final Stack<Bullet> bulletStack;
    private float dx, dy, radio, speedX, speedY;
    private boolean isCollided;
    private ImageIcon bullet;
    private Mapcanvas mapcanvas;

    public Bullet(float dx, float dy, float speedX, float speedY, float radio, Mapcanvas mapcanvas, Stack<Bullet> bulletStack) {
        this.dx = dx;
        this.dy = dy;
        this.speedX = speedX;
        this.speedY = speedY;
        this.radio = radio;
        this.mapcanvas = mapcanvas;
        this.bulletStack = bulletStack;
        this.bullet = new ImageIcon(new SpriteSheetHandler("res/clash2.png")
                .getImageWithoutCropping());
    }

    public float getDx() {
        return dx;
    }

    public void setDx(float dx) {
        this.dx = dx;
    }

    public float getDy() {
        return dy;
    }

    public void setDy(float dy) {
        this.dy = dy;
    }

    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public float getRadio(){
        return radio;
    }

    public void setRadio(float radio){
        this.radio = radio;
    }

    public synchronized void paint(Graphics2D gr2D) {
        gr2D.drawImage(this.bullet.getImage(), (int) getDx(), (int) getDy(), null);

        if (isCollided) {
            gr2D.drawImage(new ImageIcon(new SpriteSheetHandler("res/explosion03.png").crop(3, 0, 128, 128)).getImage()
                    , (int) getDx() - 45, (int) getDy() - 35, null);
            playSound("res/sounds/explosion_sound.wav");
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
