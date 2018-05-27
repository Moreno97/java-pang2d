package sprites;

import mapanel.Mapcanvas;

import java.awt.*;
import java.util.Stack;

/**
 * Created by Antonio Moreno Valls
 **/
public class Block extends Thread {
    private float dx, dy;
    private int width, height;
    private final Stack<Block> blockStack;
    private boolean isCollided;
    private Mapcanvas mapcanvas;

    public Block(float dx, float dy, int width, int height, Mapcanvas mapcanvas, Stack<Block> blockStack) {
        this.dx = dx;
        this.dy = dy;
        this.width = width;
        this.height = height;
        this.mapcanvas = mapcanvas;
        this.blockStack = blockStack;
    }

    public float getDx() {
        return dx;
    }

    public float getDy() {
        return dy;
    }


    public void setDx(float dx) {
        this.dx = dx;
    }

    public void setDy(float dy) {
        this.dy = dy;
    }

    public synchronized void paint(Graphics2D gr2D) {
        gr2D.setColor(Color.ORANGE);
        gr2D.fillRect((int) getDx(), (int) getDy(), getWidth(), getHeight());
    }

    public void setIsCollided(boolean isCollided) {
        this.isCollided = isCollided;
    }

    @Override
    public void run() {
//        while (true) {
//            Collision.checkBulletToBlockCollision(this, this.mapcanvas);
//            try {
//                Thread.sleep(20);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(Bullet.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
