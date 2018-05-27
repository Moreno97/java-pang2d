package mapanel;

import gfx.SpriteSheetHandler;
import sprites.Bullet;
import sprites.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.net.URL;
import java.util.Stack;

import static pang2d.Utils.playSound;

public class Mapcanvas extends Canvas implements Runnable{
    private Clock clk;
    private Image imgBgd;
    private URL urlimgBgd;
    private boolean start = true;
    private Boolean boclock=false;
    private static int widthC = 853, heightC = 651;
    private int titbl = 2;
    private Player player;
    private final Stack<Bullet> bulletStack;

    public Mapcanvas() {
        bulletStack = new Stack<>();
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
                    player.toRight();
                }

                if (e.getKeyCode() == (KeyEvent.VK_A)) {
                    player.toLeft();
                }

                if (e.getKeyCode() == (KeyEvent.VK_K)) {
                    Bullet b = new Bullet(player.getDx() + 45, player.getDy(), 0, 8, 10, 1,
                            mapcanvas, bulletStack);
                    playSound("res/sounds/weapon.wav");

                    // If bullets on screen is more than 3, don't allow player to shoot more
                    if (bulletStack.size() <= 2) {
                        bulletStack.push(b);
                        b.start();
                    }
                }
                if (e.getKeyCode() == (KeyEvent.VK_ENTER)) {
                    start = false;
                    startGame();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

        private void startGame() {
            imgBgd = new SpriteSheetHandler("res/imglevels/lv1.png").getImageWithoutCropping();
            boclock = true;
            clk =new Clock(121,true);
            new Thread(clk).start();
        }


    private void initSprites() {
        player = new Player((getWidth() / 2) - 20, getBounds().height - 190, 100, 100, this);
        new Thread(player).start();
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
        gr2D.drawImage(imgBgd, 0 , 0, this);
        if(!start){
            player.drawCharacter(gr2D);
        }

        synchronized (bulletStack) {
            for (Bullet bullet : bulletStack) {
                bullet.paint(gr2D);
            }
        }

        if(boclock) {
            gr2D.setColor(Color.WHITE);
            gr2D.setFont(new Font("Times New Roman",Font.BOLD,40));
            gr2D.drawString("TIME:"+String.valueOf(clk.getSegundos()), 30, 625);
        }
        bs.show();
        gr2D.dispose();
    }

    @Override
    public void run() {
        this.createBufferStrategy(2);
        try {
            while (start) {
                if (titbl == 1) {
                    imgBgd = new SpriteSheetHandler("res/imglevels/PantIni.png").getImageWithoutCropping();
                    titbl = 2;
                    paint();
                    Thread.sleep(1000);

                } else if (titbl == 2) {
                    imgBgd = new SpriteSheetHandler("res/imglevels/PantIni2.png").getImageWithoutCropping();
                    titbl = 1;
                    paint();
                    Thread.sleep(1000);
                }
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



