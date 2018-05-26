package sprites;

import java.awt.*;

public abstract class Sprite implements Runnable {
    private Image image;
    private int life;
    private int dx, dy, x, y;

    Sprite(int dx, int dy, int x, int y) {
        this.life = 3;
        this.dx = dx;
        this.dy = dy;
        this.x = x;
        this.y = y;
    }

    @Override
    public void run() {

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
}
