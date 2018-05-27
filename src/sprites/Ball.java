package sprites;

import gfx.SpriteSheetHandler;
import mapanel.Collision;
import mapanel.Mapcanvas;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ball extends Thread {

    private int dx, dy, x, y, speedX, speedY;
    private Mapcanvas game;
    private ImageIcon img;
    private final Stack<Ball> ballStack;


    public Ball(int dx, int dy, int x, int y, int speedX, int speedY, Mapcanvas game, Stack<Ball> ballStack) {
        this.dx = dx;
        this.dy = dy;
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
        this.game = game;
        this.ballStack = ballStack;
        this.img = new ImageIcon(new SpriteSheetHandler("res/ballons.png").crop(2, 3, 66, 66));
    }

    public int getDx() {
        return dx;
    }

    public int getX(){
        return x;
    }

    public int getDy() {
        return dy;
    }

    public int getY(){
        return y;
    }

    public float getSpeedX() {
        return speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void draw(Graphics2D gr2D) {
        gr2D.drawImage(img.getImage(), getDx(), getDy(), getX(), getY(), null);
    }

    @Override
    public void run() {
        try {
            while (true) {
                //Collision
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, e);
        }
    }


}
