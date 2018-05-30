package sprites;

import gfx.SpriteSheetHandler;
import mapanel.Collision;
import mapanel.Mapcanvas;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

import static mapanel.Collision.checkBall2BlockCollision;
import static mapanel.Collision.checkBall2PlayerCollision;
import static mapanel.Collision.checkBall2WallCollision;

public class Ball extends Thread {

    private int x, y , dx, dy, radio, speedX, speedY, health;
    private Mapcanvas game;
    private ImageIcon img;
    private Stack<Block> blockStack;
    private Stack<Ball> ballStack;


    public Ball(int dx, int dy, int x, int y, int health, int speedX, int speedY, Mapcanvas game, Stack<Block> blockStack, Stack<Ball> ballStack) {
        //super(dx, dy, x, y);
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.health = health;
        this.radio = x/3;
        this.speedX = speedX;
        this.speedY = speedY;
        this.game = game;
        this.blockStack = blockStack;
        this.ballStack = ballStack;
        this.img = new ImageIcon(new SpriteSheetHandler("res/ballon.png").crop(0, 0, 39, 40));
    }

    public int getX(){
        return x;
    }

    public void setX(int x){
        this.x=x;
    }

    public int getY(){
        return y;
    }

    public void setY(int y){
        this.y=y;
    }

    public int getHealth(){
        return this.health;
    }

    public void setHealth(int health){
        this.health = health;
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

    public float getSpeedX() {
        return speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedX(int i){
        this.speedX = i;
    }

    public void setspeedY(int i){
        this.speedY = i;
    }

    public float getRadio(){
        return radio;
    }

    private void movementX() {
        this.dx += this.speedX;
    }

    private void movementY(){
        this.dy += this.speedY;
    }

    public void draw(Graphics2D gr2D) {
        gr2D.drawImage(img.getImage(), getDx(), getDy(), getX(), getY(), null);
    }

    public void remove() {
        synchronized (ballStack) {
            if (!ballStack.isEmpty()) {
                    ballStack.remove(this);

            }
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                //Collision
                Collision.checkBall2BlockCollision(this, blockStack);
                Collision.checkBall2WallCollision(this, this.game);
                Collision.checkBall2BallCollision(this, ballStack);

                movementX();
                movementY();
                Thread.sleep(10);
            }

        } catch (InterruptedException e) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
