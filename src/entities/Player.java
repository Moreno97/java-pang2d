package entities;

import controller.InputHandler;
import pang2d.Space;

public class Player extends Sprite {

    private InputHandler inputHandler;

    public Player(Space level, int dx, int dy, InputHandler inputHandler) {
        super(level, "Player", dx, dy, 2);
        this.inputHandler = inputHandler;
    }

    @Override
    public void tick() {
        int xa = 0;
        int ya = 0;

//        if (inputHandler.up.isPressed()) {
//            ya--;
//        }
//        if (inputHandler.down.isPressed()) {
//            ya++;
//        }
//        if (inputHandler.left.isPressed()) {
//            xa--;
//        }
//        if (inputHandler.right.isPressed()) {
//            xa++;
//        }

        if (xa != 0 || ya != 0) {
            move(xa, ya);
            isMoving = true;
        } else {
            isMoving = false;
        }
    }

    @Override
    public void render(Space level) {
        int xTile = 0;
        int yTile = 28;
    }

    @Override
    public boolean hasCollided(int xa, int ya) {
        return false;
    }
}
