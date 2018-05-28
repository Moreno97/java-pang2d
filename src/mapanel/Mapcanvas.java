package mapanel;

import gfx.SpriteSheetHandler;
import sprites.Ball;
import sprites.Bullet;
import sprites.Player;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.net.URL;
import java.util.Stack;

import static pang2d.Utils.playSound;

public class Mapcanvas extends Canvas implements Runnable {
    private static int widthC = 853, heightC = 651;
    private final Stack<Bullet> bulletStack;
    private final Stack<Ball> ballStack;
    private Clock clk;
    private Image imgBgd, gun, gover, lifechar;
    private URL urlimgBgd;
    private boolean startI = false;
    private boolean startG = true;
    private boolean over = true;
    private boolean boclock = false;
    private int titbl = 2;
    private Player player;

    public Mapcanvas() {
        bulletStack = new Stack<>();
        ballStack = new Stack<>();
        initCanvas();
    }

    private void initCanvas() {
        imgBgd = new SpriteSheetHandler("res/imglevels/PantIni.png").getImageWithoutCropping();
        setSize(widthC, heightC);
        initSprites();
        setFocusable(true);

        final Mapcanvas mapcanvas = this;
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == (KeyEvent.VK_D)) {
                    if (startI) {
                        player.toRight();
                    }
                }

                if (e.getKeyCode() == (KeyEvent.VK_A)) {
                    if (startI) {
                        player.toLeft();
                    }
                }

                if (e.getKeyCode() == (KeyEvent.VK_K)) {
                    if (startI) {
                        Bullet b = new Bullet(player.getDx() + 45, player.getDy(), 0, 8, 10, 1,
                                mapcanvas, bulletStack, new Stack<>());
                        playSound("res/sounds/weapon.wav");

                        // If bullets on screen is more than 3, don't allow player to shoot more
                        if (bulletStack.size() <= 2) {
                            bulletStack.push(b);
                            b.start();
                        }
                    }
                }

                if (e.getKeyCode() == (KeyEvent.VK_ENTER)) {
                    startI = true;
                    startGame();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    private void startGame() {
        if (startI && startG) {
            over = true;
            imgBgd = new SpriteSheetHandler("res/imglevels/lv1.png").getImageWithoutCropping();
            gun = new SpriteSheetHandler("res/pistola.png").getImageWithoutCropping();
            lifechar = new SpriteSheetHandler("res/minichar.png").getImageWithoutCropping();
            boclock = true;
            clk = new Clock(10, true);
            new Thread(clk).start();
            startG = false;
        }

    }

    private void initSprites() {
        player = new Player((getWidth() / 2) - 20, getBounds().height - 190, 100, 100, this);
        new Thread(player).start();

        for (int i = 0; i < 1; i++) {
            Ball b = new Ball(0 + 40 * (i + 1), 0 + 40 * (i + 1), 60, 60, 42, 2, 2, this, player);
            ballStack.push(b);
            b.start();
        }

    }

    private BufferStrategy getBuffer() {
        return getBufferStrategy();
    }

    private synchronized void paint() {
        BufferStrategy bs;
        bs = getBuffer();

        if (bs == null) {
            System.err.println("Error en la recuperación del BufferStrategy");
            return;
        }
        if (widthC <= 0 || heightC <= 0) {
            System.out.println("Map size error: (" + widthC + "x" + heightC + ")");
            return;
        }
        Graphics2D gr2D = (Graphics2D) bs.getDrawGraphics();
        gr2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        gr2D.drawImage(imgBgd, 0, 0, this);


        if (startI) {
            player.drawCharacter(gr2D);
            gr2D.drawImage(gun, (getWidth() / 2) - 27, getBounds().height - 55, this);
            gr2D.drawImage(lifechar, getWidth() - 120, getBounds().height - 60, this);
            gr2D.setColor(Color.WHITE);
            gr2D.setFont(new Font("Times New Roman", Font.BOLD, 30));
            gr2D.drawString("X 3", getWidth() - 78, getBounds().height - 28);
            gr2D.setFont(new Font("Times New Roman", Font.BOLD, 20));
            gr2D.drawString("SCORE", getWidth() - 350, getBounds().height - 55);

        }

        synchronized (ballStack) {
            for (Ball ball : ballStack) {
                ball.draw(gr2D);
            }
        }
            synchronized (bulletStack) {
                for (Bullet bullet : bulletStack) {
                    bullet.paint(gr2D);
                }
            }

            bs.show();
            gr2D.dispose();
        }

    private void soundGameOver() {
        if (over) {
            over = false;
            playSound("res/levels/sounds/GameOver_Triste.wav");
        }
    }

    @Override
    public void run() {
        this.createBufferStrategy(2);
        try {
            while (!startI) {
                if (titbl == 1) {
                    imgBgd = new SpriteSheetHandler("res/imglevels/PantIni.png").getImageWithoutCropping();
                    titbl = 2;

                } else if (titbl == 2) {
                    imgBgd = new SpriteSheetHandler("res/imglevels/PantIni2.png").getImageWithoutCropping();
                    titbl = 1;
                }
                paint();
                Thread.sleep(500);
            }
            while (true) {
                paint();
                Thread.sleep(10);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*private void mapLvs(int cod) {
        switch (cod) {
            case 2:
                urlimgBgd = this.getClass().getResource("images/lv2.png");
                imgBgd = new ImageIcon(urlimgBgd).getImage();
                break;
            case 3:
                urlimgBgd = this.getClass().getResource("images/lv3.png");
                imgBgd = new ImageIcon(urlimgBgd).getImage();
                break;
            case 4:
                urlimgBgd = this.getClass().getResource("images/lv4.png");
                imgBgd = new ImageIcon(urlimgBgd).getImage();
                break;
        }

    }*/


}
