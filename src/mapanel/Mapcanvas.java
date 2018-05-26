package mapanel;

import sprites.Bullet;
import sprites.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.net.URL;
import java.util.Stack;

public class Mapcanvas extends Canvas implements Runnable {
    private Image imgBgd;
    private URL urlimgBgd;
    private boolean start = true;
    private static int widthC = 850, heightC = 570;
    private int titbl = 2;
    private Player player;
    private final Stack<Bullet> bulletStack;

    public Mapcanvas() {
        bulletStack = new Stack<>();
        initCanvas();
    }

    private void initCanvas() {
        urlimgBgd = this.getClass().getResource("images/lv1.png");
        imgBgd = new ImageIcon(urlimgBgd).getImage();
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
                    Bullet b = new Bullet(player.getDx() + 40, player.getDy(), 0, 8, 10, 1,
                            mapcanvas, bulletStack);

                    // If bullets on screen is more than 3, don't allow player to shoot more
                    if (bulletStack.size() <= 2) {
                        bulletStack.push(b);
                        b.start();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

        });


    }

    private void initSprites() {
        player = new Player((getWidth() / 2) - 20, getBounds().height - 90, 66, 66, this);
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
        gr2D.drawImage(imgBgd, 0, 0, this);
        player.drawCharacter(gr2D);

        synchronized (bulletStack) {
            for (Bullet bullet : bulletStack) {
                bullet.paint(gr2D);
            }
        }

        bs.show();
        gr2D.dispose();
    }

    @Override
    public void run() {
        this.createBufferStrategy(2);
        while (true) {
            paint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
