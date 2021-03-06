package mapanel;

import controller.Server;
import gfx.SpriteSheetHandler;
import sprites.Ball;
import sprites.Block;
import sprites.Bullet;
import sprites.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.Stack;

import static pang2d.Utils.playSound;

public class Mapcanvas extends Canvas implements Runnable {
    private Clock clk;
    private Image imgBgd, gun, gover, stgclear, lifechar, lifechar2;
    private boolean startI = false;
    private boolean startG = true;
    private boolean over = false, clear = false;
    private boolean boclock = false;
    private static int widthC = 853, heightC = 651;
    private int titbl = 2;
    private int scr = 0;
    private final Stack<Player> playerStack;
    private final Stack<Bullet> bulletStack;
    private Stack<Block> blockStack;
    private final Stack<Ball> ballStack;
    private Server server;
    private Player mainPlayer;

    public Mapcanvas() {
        this.blockStack = new Stack<>();
        this.bulletStack = new Stack<>();
        this.ballStack = new Stack<>();
        this.playerStack = new Stack<>();
        initCanvas();
        server = new Server(playerStack, getBounds().height, this);
        server.start();
    }


    public void shootBullet(Player player) {
        Bullet b = new Bullet(player.getDx() + 45, player.getDy(),
                0, 8, 10, 1, this);

        // If bullets on screen is more than 3 multiplied by each player, don't allow players to shoot more
        if (bulletStack.size() <= playerStack.size() * 2) {
            bulletStack.push(b);
            b.start();
            playSound("res/sounds/weapon.wav");
        }
    }

    public boolean isStartI() {
        return startI;
    }

    private void initCanvas() {
        imgBgd = new SpriteSheetHandler("res/imglevels/PantIni.png").getImageWithoutCropping();
        setSize(widthC, heightC);
        initSprites();
        setFocusable(true);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_D && (startI & over)) {
                    playerStack.get(0).toRight();
                }

                if (e.getKeyCode() == KeyEvent.VK_A && (startI & over)) {
                    playerStack.get(0).toLeft();
                }

                if (e.getKeyCode() == KeyEvent.VK_K && (startI & over)) {
                    shootBullet(mainPlayer);
                }

                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    startG = true;
                    if (startI) {
                        restart();
                    }

                    startI = true;
                    startGame();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    private void restart() {
        startGame();
        playerStack.clear(); // Remove all elements from the list first
        mainPlayer = new Player((getWidth() / 2) - 20, getBounds().height - 190, 100,
                100, this, 1, new ImageIcon(new SpriteSheetHandler("res/sprites.png").crop(3, 1, 47, 49)));
        playerStack.add(mainPlayer);
    }

    private void startGame() {
        if (startI && startG) {
            imgBgd = new SpriteSheetHandler("res/imglevels/lv1.png").getImageWithoutCropping();
            gun = new SpriteSheetHandler("res/pistola.png").getImageWithoutCropping();
            lifechar = new SpriteSheetHandler("res/minichar.png").getImageWithoutCropping();
            lifechar2 = new SpriteSheetHandler("res/minichar2.png").getImageWithoutCropping();
            boclock = true;
            clk = new Clock(101, true);
            new Thread(clk).start();
            startG = false;

            for (int i = 0; i < 4; i++) {
                Ball b = new Ball((int) (Math.random() * 300 * i + 10), 35, 60, 60, 3,
                        2, 2, this);
                ballStack.push(b);
                b.start();
            }
        }
    }

    private void initSprites() {
        mainPlayer = new Player((getWidth() / 2) - 20, getBounds().height - 190, 100,
                100, this, 1, new ImageIcon(new SpriteSheetHandler("res/sprites.png")
                .crop(3, 1, 47, 49)));
        playerStack.add(mainPlayer);
        new Thread(mainPlayer).start();

        for (int i = 0; i < 2; i++) {
            Block b = new Block(50 + (i * 300), 250, 80, 20, this);
            blockStack.push(b);
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
            return;
        }

        if (widthC <= 0 || heightC <= 0) {
            return;
        }

        Graphics2D gr2D = (Graphics2D) bs.getDrawGraphics();
        gr2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        gr2D.drawImage(imgBgd, 0, 0, this);

        if (startI) {
            synchronized (playerStack) {
                for (Player player : playerStack) {
                    player.drawCharacter(gr2D);
                }
            }

            gr2D.drawImage(gun, (getWidth() / 2) - 27, getBounds().height - 55, this);
            gr2D.drawImage(lifechar, getWidth() - 120, getBounds().height - 75, this);
            gr2D.setColor(Color.WHITE);

            gr2D.setFont(new Font("Times New Roman", Font.BOLD, 20));
            gr2D.drawString("X " + mainPlayer.getLife(), getWidth() - 78, getBounds().height - 47);

            if (playerStack.size() > 1) {
                gr2D.drawImage(lifechar2, getWidth() - 120, getBounds().height - 40, this);
                gr2D.drawString("X " + playerStack.peek().getLife(), getWidth() - 78, getBounds().height - 15);
            }
            gr2D.setFont(new Font("Times New Roman", Font.BOLD, 20));
            gr2D.drawString("SCORE", getWidth() - 350, getBounds().height - 55);
            gr2D.drawString("" + scr, getWidth() - 350, getBounds().height - 35);

            synchronized (ballStack) {
                for (Ball b : ballStack) {
                    b.draw(gr2D);
                }
            }

            for (Block block : blockStack) {
                block.paint(gr2D);
            }

        }

        synchronized (bulletStack) {
            for (Bullet bullet : bulletStack) {
                bullet.paint(gr2D);
            }
        }

        if (boclock) {
            if (clk.getSegundos() == 0 || mainPlayer.getLife() <= 0) {
                gr2D.setFont(new Font("Times New Roman", Font.BOLD, 40));
                gr2D.drawString("TIME:" + String.valueOf(clk.getSegundos()), 30, 625);
                gover = new SpriteSheetHandler("res/imglevels/gover.png").getImageWithoutCropping();
                gr2D.drawImage(gover, 0, 0, this);
                mainPlayer.changeCharacter(true);
                soundGameOver();

            } else if (clk.getSegundos() > 0) {
                over = true;
                gr2D.setColor(Color.WHITE);
                gr2D.setFont(new Font("Times New Roman", Font.BOLD, 40));
                gr2D.drawString("TIME:" + String.valueOf(clk.getSegundos()), 30, 625);
            }
        }
        if (startI && ballStack.empty()) {
            stgclear = new SpriteSheetHandler("res/imglevels/Stageclear.png").getImageWithoutCropping();
            gr2D.drawImage(stgclear, 0, 0, this);
            mainPlayer.changeCharacter(false);
            clk.iniStop();
            scr = +clk.getSegundos() * 150;
            gr2D.setFont(new Font("Times New Roman", Font.BOLD, 20));
            gr2D.drawString("" + scr, getWidth() - 350, getBounds().height - 355);
            soundStageClear();
        } else {
            clear = true;
            stgclear = null;
        }
        gr2D.drawImage(stgclear, 0, 0, this);
        bs.show();
        gr2D.dispose();
    }

    private void soundGameOver() {
        if (over) {
            over = false;
            playSound("res/levels/sounds/GameOver_Triste.wav");
        }
    }

    private void soundStageClear() {
        if (clear) {
            clear = false;
            playSound("res/levels/sounds/stageClear.wav");
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

    public synchronized Stack<Ball> getBalls() {
        return ballStack;
    }

    public Stack<Bullet> getBullets() {
        return bulletStack;
    }

    public Stack<Block> getBlocks() {
        return blockStack;
    }

    public Stack<Player> getPlayers() {
        return playerStack;
    }

}
