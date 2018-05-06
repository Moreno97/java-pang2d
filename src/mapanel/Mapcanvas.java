package mapanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.net.URL;

public class Mapcanvas extends Canvas implements Runnable,KeyListener {
    private Image imgBgd;
    private URL urlimgBgd;
    private Boolean start = true;
    private static int widthC = 853, heightC = 571;
    private int titbl = 2;

    public Mapcanvas() {
        InitCanvas();
    }

    private void InitCanvas() {
        urlimgBgd = this.getClass().getResource("images/PantIni.png");
        imgBgd = new ImageIcon(urlimgBgd).getImage();
        setSize(widthC, heightC);
        setFocusable(true);
        setVisible(true);
        addKeyListener(this);
    }
    private BufferStrategy getBuffer() {
        return getBufferStrategy();
    }

    public synchronized void paint() {
        BufferStrategy bs;bs = getBuffer();

        if (bs == null) {
            System.err.println("Error en la recuperación del BufferStrategy");
            return;
        }
        if ((widthC <= 0) || (heightC <= 0)) {
            System.out.println("Map size error: (" + widthC + "x" + heightC + ")");
            return;
        }
        Graphics2D gr2D = (Graphics2D) bs.getDrawGraphics();
        gr2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Pintamos el fondo
        gr2D.drawImage(imgBgd, 0, 0, this);

        bs.show();
        gr2D.dispose();
    }

    private void startGame() {
        urlimgBgd = this.getClass().getResource("images/lv1.png");
        imgBgd = new ImageIcon(urlimgBgd).getImage();
    }

    private void mapLvs(int cod) {
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

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int cod = e.getKeyCode();
        if (cod == 10 && start) {
            System.out.println(cod);
            start = false;
            startGame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        this.createBufferStrategy(2);

        try {
            while (start) {
                if (titbl == 1) {
                    urlimgBgd = this.getClass().getResource("images/PantIni.png");
                    imgBgd = new ImageIcon(urlimgBgd).getImage();
                    titbl = 2;
                    paint();
                    Thread.sleep(1000);

                } else if (titbl == 2) {
                    urlimgBgd = this.getClass().getResource("images/PantIni2.png");
                    imgBgd = new ImageIcon(urlimgBgd).getImage();
                    titbl = 1;
                    paint();
                    Thread.sleep(1000);
                }
            }
        } catch (Exception e) {
        }
        
        try {
            while (true) {
                if (start){
                    urlimgBgd = this.getClass().getResource("images/PantInit.png");
                    imgBgd = new ImageIcon(urlimgBgd).getImage();
                }
                paint();
                Thread.sleep(15);
            }
        } catch (Exception e) {
        }

    }

}
