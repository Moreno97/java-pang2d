package sprites;

import mapanel.Collision;
import mapanel.Mapcanvas;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Antonio Moreno Valls
 **/
public class Bullet extends Thread {
    private float dx, dy, speedX, speedY, radio, mass;
    private int index;
    private Mapcanvas mapcanvas;

    public Bullet(float dx, float dy, float speedX, float speedY, float radio, float mass, Mapcanvas mapcanvas, int index) {
        this.dx = dx;
        this.dy = dy;
        this.speedX = speedX;
        this.speedY = speedY;
        this.radio = radio;
        this.mass = mass;
        this.mapcanvas = mapcanvas;
        this.index = index;
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

    public void paint(Graphics2D gr2D) {
        gr2D.setColor(Color.RED);
        gr2D.fillOval((int) (this.dx - radio), (int) (this.dy - radio), (int) radio * 2, (int) radio * 2);
    }

    private float restDy() {
        return this.dy -= this.speedY;
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
        synchronized (mapcanvas.bulletsArrayList) {
            if (mapcanvas.bulletsArrayList.size() == index) {
                mapcanvas.bulletsArrayList.remove(index);
            }
        }
    }
}
