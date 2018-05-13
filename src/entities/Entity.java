package entities;

import pang2d.Space;

public abstract class Entity {
    public int dx, dy;
    public Space level;

    public Entity(Space level) {
        this.level = level;
    }

    public abstract void tick();
    public abstract void render(Space level);
}
