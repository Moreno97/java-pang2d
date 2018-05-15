package controller;

import mapanel.Mapanel;
import sprites.Player;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class InputHandler implements KeyListener {
    public class Key {
        public boolean isPressed = false;

        public boolean isPressed() {
            return isPressed;
        }

        private void toogle(boolean isPressed) {
            this.isPressed = isPressed;
        }
    }

    public List<Key> keys = new ArrayList<>();
    public Key right = new Key();
    public Key left = new Key();
    public Key up = new Key();
    public Key down = new Key();
    private Player player;
    private Mapanel panel;

    public InputHandler(Player player, Mapanel panel) {
        this.player = player;
        this.panel = panel;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        toogleKey(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        toogleKey(e.getKeyCode(), false);
    }

    private void toogleKey(int keyCode, boolean isPressed) {
        switch (keyCode) {
            case KeyEvent.VK_W:
//                up.toogle(isPressed);
                break;
            case KeyEvent.VK_S:
                down.toogle(isPressed);
                break;
            case KeyEvent.VK_A:
                left.toogle(isPressed);
                break;
            case KeyEvent.VK_D:
                right.toogle(isPressed);
                player.toRight((Graphics2D) panel.getGraphics());
                break;
        }
    }
}
