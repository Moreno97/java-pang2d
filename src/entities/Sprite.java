package entities;


import pang2d.Space;

public abstract class Sprite extends Entity {
    protected String name;
    protected int speed;
    protected int numSteps;
    protected boolean isMoving;
    protected int movingDir = 1;
    protected int scale = 1;

    public Sprite(Space level, String name, int dx, int dy, int speed) {
        super(level);
        this.name = name;
        this.dx = dx;
        this.dy = dy;
        this.speed = speed;
    }

    public void move(int xa, int ya) {
        if (xa != 0 && ya != 0) {
            move(xa, 0);
            move(ya, 0);
            numSteps--;
            return;
        }
        numSteps++;
        if (!hasCollided(xa, ya)) {
            if (ya < 0) {
                movingDir = 0;
            }
            if (ya > 0) {
                movingDir = 1;
            }
            if (xa < 0) {
                movingDir = 2;
            }
            if (xa > 0) {
                movingDir = 3;
            }
            dx += xa * speed;
            dy += ya * speed;
        }
    }

    public abstract boolean hasCollided(int xa, int ya);

    public String getName() {
        return name;
    }
}
