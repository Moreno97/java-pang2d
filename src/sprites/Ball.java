package sprites;

import gfx.SpriteSheetHandler;
import mapanel.Collision;
import mapanel.Mapcanvas;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import static mapanel.Collision.checkBall2PlayerCollision;
import static mapanel.Collision.checkBall2WallCollision;

public class Ball extends Thread {

    private int x, y , dx, dy, radio, speedX, speedY;
    private Mapcanvas game;
    private ImageIcon img;
    private Player cg;
    public Ball actualBall = this;


    public Ball(int dx, int dy, int x, int y, int radio, int speedX, int speedY, Mapcanvas game, Player cg) {
        //super(dx, dy, x, y);
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.radio = radio;
        this.cg = cg;
        this.speedX = speedX;
        this.speedY = speedY;
        this.game = game;
        this.img = new ImageIcon(new SpriteSheetHandler("res/ballon.png").crop(0, 0, 39, 40));
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
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
        // TODO: Set physics to reduce bullet speed
        this.dx += this.speedX;
    }

    private void movementY(){
        this.dy += this.speedY;
    }

    public void draw(Graphics2D gr2D) {
        gr2D.drawImage(img.getImage(), getDx(), getDy(), getX(), getY(), null);
    }

    @Override
    public void run() {
        try {
            while (true) {
                //Collision
                checkBall2WallCollision(this, this.game);
                Timer time = new Timer();
                time.schedule(new TimerTask() {
                    @Override
                    public void run() {

                        if (checkBall2PlayerCollision(actualBall, cg)) {
                            cg.setLife(cg.getLife() - 1);
                            System.out.println(cg.getLife());
                        }

                        }

                }, 0,50000);

                movementX();
                movementY();
                Thread.sleep(10);
            }

        } catch (InterruptedException e) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
