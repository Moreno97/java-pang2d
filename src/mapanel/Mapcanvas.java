package mapanel;

import controller.InputHandler;
import sprites.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.net.URL;
import java.util.HashSet;

public class Mapcanvas extends Canvas implements Runnable {
    private Image imgBgd;
    private URL urlimgBgd;
    private boolean start = true;
    private static int widthC = 850, heightC = 570;
    private int titbl = 2;
    private Player player;
    private InputHandler inputHandler;

    public Mapcanvas() {
        InitCanvas();
    }

    private void InitCanvas() {
        urlimgBgd = this.getClass().getResource("images/lv1.png");
        imgBgd = new ImageIcon(urlimgBgd).getImage();
        setSize(widthC, heightC);
        setFocusable(true);
        setVisible(true);
        inputHandler = new InputHandler(this);
        player = new Player(this, inputHandler);
        addKeyListener(inputHandler);
    }

    private void tick() {
        HashSet activeKeys = inputHandler.getActiveKeys();
        if (activeKeys.contains(KeyEvent.VK_D)) {
            player.toRight();
        }

        if (activeKeys.contains(KeyEvent.VK_A)) {
            player.toLeft();
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

        // Pinta el background de negro
        gr2D.setColor(Color.BLACK);
        gr2D.fillRect(0, 0, widthC, heightC);
        gr2D.drawImage(imgBgd, 0, 0, this);
        player.drawCharacter(gr2D);

        bs.show();
        gr2D.dispose();
    }

    private void startGame() {
        urlimgBgd = this.getClass().getResource("images/lv1.png");
        imgBgd = new ImageIcon(urlimgBgd).getImage();
    }

//    @Override
//    public void keyTyped(KeyEvent e) {
//
//    }
//
//    @Override
//    public void keyPressed(KeyEvent e) {
//        int cod = e.getKeyCode();
//        if (cod == 10 && start) {
//            start = false;
//            startGame();
//        }
//    }
//
//    @Override
//    public void keyReleased(KeyEvent e) {
//
//    }

    @Override
    public void run() {
        this.createBufferStrategy(2);
//        try {
//            while (start) {
//                if (titbl == 1) {
//                    urlimgBgd = this.getClass().getResource("images/PantIni.png");
//                    imgBgd = new ImageIcon(urlimgBgd).getImage();
//                    titbl = 2;
//                    paint();
//
//                } else if (titbl == 2) {
//                    urlimgBgd = this.getClass().getResource("images/PantIni2.png");
//                    imgBgd = new ImageIcon(urlimgBgd).getImage();
//                    titbl = 1;
//                    paint();
//                }
//                Thread.sleep(1000);
//
//            }
//        } catch (Exception e) {
//        }

        while (true) {
            tick();
            paint();

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
