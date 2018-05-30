package mapanel;

import javafx.scene.shape.Circle;
import sprites.Ball;
import sprites.Block;
import sprites.Bullet;
import sprites.Player;

import java.util.Stack;

public class Collision {

    public static boolean ballplayercollided = false;

//    public static synchronized void checkBall2BallCollision(Item i, ArrayList<Item> items) {
//        // Variables usadas en las comrobaciones
//        double r, d_mod;
//        Vec2d d;
//
//        for (Item ball : items) { // Comprueba respecto a todas las bolas del espacio
//            if (ball != i) {     // exceptuando la propia bola
//
//                r = i.radio + ball.radio; // Suma de los radios de las bolas, para compro
//                d = new Vec2d(ball.dx - i.dx, ball.dy - i.dy); // Vector de distancia entre centros
//                d_mod = Math.hypot(d.x, d.y); // Modulo del vector de distancia
//
//                if (d_mod <= r) { // Si el modulo del vector de distancia es menor a la suma de los radios
//                    calcBounce(i, ball);
//                }
//            }
//        }
//    }

//    // Nuevo sistema de colisiones
//    public static void calcBounce(Item it1, Item it2) {
//        // Ángulo de colision entre las bolas
//        double collAngle = Math.atan2((it2.dy - it1.dy), (it2.dx - it1.dx));
//        // Velocidad de la bola 1
//        Vec2d v_b1 = new Vec2d(it1.getSpeedX(), it1.getSpeedY());
//        double mod_v_b1 = Math.hypot(v_b1.x, v_b1.y);
//        // Velocidad de la bola 2
//        Vec2d v_b2 = new Vec2d(it2.getSpeedX(), it2.getSpeedY());
//        double mod_v_b2 = Math.hypot(v_b2.x, v_b2.y);
//        // Calcula direcciones
//        double d1 = Math.atan2(v_b1.y, v_b1.x);
//        double d2 = Math.atan2(v_b2.y, v_b2.x);
//        // Calcula las nuevas velocidades relativas
//        double new_xSpeed_b1 = mod_v_b1 * Math.cos(d1 - collAngle);
//        double new_ySpeed_b1 = mod_v_b1 * Math.sin(d1 - collAngle);
//        double new_xSpeed_b2 = mod_v_b2 * Math.cos(d2 - collAngle);
//        double new_ySpeed_b2 = mod_v_b2 * Math.sin(d2 - collAngle);
//        // Calcula las nuevas velocidades finales
//        double fin_xSpeed_b1 = ((it1.mass - it2.mass) * new_xSpeed_b1 + (2 * it2.mass) * new_xSpeed_b2) / (it1.mass + it2.mass);
//        double fin_xSpeed_b2 = ((2 * it1.mass) * new_xSpeed_b1 + (it2.mass - it1.mass) * new_xSpeed_b2) / (it1.mass + it2.mass);
//        double fin_ySpeed_b1 = new_ySpeed_b1;
//        double fin_ySpeed_b2 = new_ySpeed_b2;
//        // Aplica las velocidades finales al ángulo de posicion
//        it1.setSpeedX((float) (Math.cos(collAngle) * fin_xSpeed_b1 - Math.sin(collAngle) * fin_ySpeed_b1));
//        it1.setSpeedY((float) (Math.sin(collAngle) * fin_xSpeed_b1 + Math.cos(collAngle) * fin_ySpeed_b1));
//        it2.setSpeedX((float) (Math.cos(collAngle) * fin_xSpeed_b2 - Math.sin(collAngle) * fin_ySpeed_b1));
//        it2.setSpeedY((float) (Math.sin(collAngle) * fin_xSpeed_b2 + Math.cos(collAngle) * fin_ySpeed_b2));
//        // Pone las posiciones de las bolas como vectores para facilitar el cálculo
//        Vec2d pos_b1 = new Vec2d(it1.dx, it1.dy);
//        Vec2d pos_b2 = new Vec2d(it2.dx, it2.dy);
//        // Calcula las diferencias entre las posiciones de las bolas
//        Vec2d posDiff = new Vec2d(pos_b1.x - pos_b2.x, pos_b1.y - pos_b2.y);
//        double mod_posDiff = Math.hypot(posDiff.x, posDiff.y);
//        double scale = (((it1.radio + it2.radio) - mod_posDiff) / mod_posDiff);
//        Vec2d mtd = new Vec2d(posDiff.x * scale, posDiff.y * scale);
//        // Calcula las inversas de las masas de las bolas
//        double inv_mass_b1 = 1 / it1.mass;
//        double inv_mass_b2 = 1 / it2.mass;
//        // Calcula las nuevas posiciones para evitar bugs de solapamiento de las bolas
//        pos_b1 = new Vec2d(pos_b1.x + (mtd.x * (inv_mass_b1 / (inv_mass_b1 + inv_mass_b2))),
//                            pos_b1.y + (mtd.y * (inv_mass_b1 / (inv_mass_b1 + inv_mass_b2))));
//        pos_b2 = new Vec2d(pos_b2.x - (mtd.x * (inv_mass_b2 / (inv_mass_b1 + inv_mass_b2))),
//                            pos_b2.y - (mtd.y * (inv_mass_b2 / (inv_mass_b1 + inv_mass_b2))));
//        // Establece las nuevas posiciones
//        it1.dx = (float) pos_b1.x;
//        it1.dy = (float) pos_b1.y;
//        it2.dx = (float) pos_b2.x;
//        it2.dy = (float) pos_b2.y;
//    }

//    public static void checkBall2BlockCollision(Item i, ArrayList<BlackHole> bholes) {
//        bholes.stream().filter((b) -> (b.inside(i) || b.getItem() == i)).forEachOrdered((b) -> {
//            try {
//                b.checkStop(i);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(Collision.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        });
//    }

//    // Colisión entre el jugador y un objeto de tipo 'Block'.
//    public static void checkBall2BlockCollision(CharacterGame cg, ArrayList<Block> blocks) {
//        Circle c = new Circle(cg.getDx(), cg.getDy(), cg.getX());
//        blocks.stream().map((obs) -> {
//            if (cg.getDx() >= obs.posX && cg.getDx() <= obs.width + obs.posX && cg.getDy()>= obs.posY && cg.getDy() <= obs.height + obs.posY) {
//
//            }
//            return obs;
//        }).filter((obs) -> (c.intersects(obs.posX, obs.posY, obs.width, obs.height
//        ))).map((obs) -> {
//            if (cg.getDx() <= obs.posX || cg.getDx() >= obs.width + obs.posX) {
//                cg.rebotaX();
//            }
//            return obs;
//        }).filter((obs) -> (cg.getDy() <= obs.posY || cg.getDy() >= obs.height + obs.posY)).forEachOrdered((_item) -> {
//            cg.rebotaY();
//        });
//    }

    //    // Colisión entre el Item y un objeto de tipo 'BlackHole'.
//    public static void checkBall2HoleCollision(Item i, ArrayList<BlackHole> obstacles) {
//        Circle c = new Circle(i.dx, i.dy, i.radio);
//        for (BlackHole m : obstacles) {
//            if (c.intersects(m.getDx(), m.getDy(), m.radio, m.radio)) {
//                if (i.getDx() <= m.getDx() || i.getDx() >= m.radio + m.getDx()) {
//                    System.out.println("Choque en el EJE X");
//                    i.rebotaX();
//                }
//                if (i.getDy() <= m.getDy() || i.getDy() >= m.radio + m.getDy()) {
//                    System.out.println("Choque en el EJE Y");
//                    i.rebotaY();
//                }
//            }
//        }
//    }

    public static void checkBall2BallCollision(Ball b, Stack<Ball> ballStack){
        for (Ball ball : ballStack){
            if(b != ball){
                Circle c = new Circle(b.getDx(), b.getDy(), b.getRadio());

                if (c.intersects(ball.getDx(), ball.getDy(), ball.getX(), ball.getY())) {
                    if (b.getDx() <= ball.getDx() || b.getDx() >= ball.getX() + ball.getDx()) {
                        b.setSpeedX((int) -b.getSpeedX());
                        ball.setSpeedX((int) -ball.getSpeedX());
                    }
                    if (b.getDy() <= ball.getDy() || b.getDy() >= ball.getY() + ball.getDy()) {
                        b.setspeedY((int) -b.getSpeedY());
                        ball.setspeedY((int) -ball.getSpeedY());
                    }
                }

            }


        }

    }

    public static void checkBullet2BlockCollision(Bullet i, Stack<Block> blockStack) {
        Circle c = new Circle(i.getDx(), i.getDy(), i.getRadio());
        for (Block m : blockStack) {
            if (c.intersects(m.getDx(), m.getDy(), m.getWidth(), m.getHeight())) {
                if (i.getDx() <= m.getDx() || i.getDx() >= m.getWidth() + m.getDx()) {
                    i.setIsCollided(true);
                    i.remove();
                }

                if (i.getDy() <= m.getDy() || i.getDy() >= m.getWidth() + m.getDy()) {
                    i.setIsCollided(true);
                    i.remove();
                }
            }
        }
    }

    public static void checkBullet2BallCollision(Bullet i, Stack<Ball> ballStack) {
        Circle c = new Circle(i.getDx(), i.getDy(), i.getRadio());

        for (Ball b : ballStack) {
            if (c.intersects(b.getDx(), b.getDy(), b.getX(), b.getY())) {
                if (i.getDx() <= b.getDx() || i.getDx() >= b.getX() + b.getDx()) {
                    i.setIsCollided(true);
                    i.remove();
                    b.remove();

                }

                if (i.getDy() <= b.getDy() || i.getDy() >= b.getY() + b.getDy()) {
                    i.setIsCollided(true);
                    i.remove();
                    b.remove();
                    
                }
            }
        }
    }

    public static void checkBullet2WallCollision(Bullet i, Mapcanvas game) {
        if (i.getDy() + i.getRadio() >= game.getHeight() || i.getDy() - i.getRadio() <= -10) {
            i.remove();
            i.setIsCollided(true);
        }
    }

    public static void checkBall2WallCollision(Ball i, Mapcanvas game) {
        if (i.getDy() + i.getRadio() >= (game.getHeight() - 10) || i.getDy() + i.getRadio() < 20) {
            i.setspeedY((int) -i.getSpeedY());
        }

        if (i.getDx() + i.getRadio() >= (game.getWidth() - 10) || i.getDx() + i.getRadio() < 20) {
            i.setSpeedX((int) -i.getSpeedX());

        }
    }

    public static void checkBall2PlayerCollision(Stack<Ball> ballStack, Player cg) {

        for (Ball b : ballStack) {

            Circle c = new Circle(b.getDx(), b.getDy(), b.getRadio());

            if (c.intersects(cg.getDx(), cg.getDy(), cg.getX(), cg.getY())) {
                if (b.getDx() <= cg.getDx() || b.getDx() >= cg.getX() + cg.getDx()) {
                    b.setSpeedX((int) -b.getSpeedX());
                }
                if (b.getDy() <= cg.getDy() || b.getDy() >= cg.getY() + cg.getDy()) {
                    b.setspeedY((int) -b.getSpeedY());
                }
                cg.setLife(cg.getLife()-1);
                System.out.println(cg.getLife());
            }
        }

    }

    public static void checkPlayer2WallCollision(Player cg, Mapcanvas game) {
        if (cg.getDx() + cg.getX() >= game.getWidth()) {
            cg.setDx(game.getWidth() - 50);
        }

        if (cg.getDx() + cg.getX() <= game.getBounds().getX() + 30) {
            cg.setDx((int) game.getBounds().getX());
        }
    }

    public static void checkBall2BlockCollision(Ball b, Stack<Block> blockStack) {
        Circle c = new Circle(b.getDx(), b.getDy(), b.getRadio());

        for (Block m : blockStack) {
            if (c.intersects(m.getDx(), m.getDy(), m.getWidth(), m.getHeight())) {

                //b.setDx((int)(b.getDx() - b.getSpeedX()) / 20);
                //b.setDy((int)(b.getDy() - b.getSpeedY()) / 20);

                if (b.getDx() <= m.getDx() || b.getDx() >= m.getWidth() + m.getDx()) {
                    b.setSpeedX((int) -b.getSpeedX());
                }
                if (b.getDy() <= m.getDy() || b.getDy() >= m.getWidth() + m.getDy()) {
                    b.setspeedY((int) -b.getSpeedY());
                }

            }
        }


    }

}
