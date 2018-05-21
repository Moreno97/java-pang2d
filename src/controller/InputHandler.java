package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;

public class InputHandler implements KeyListener {

    private HashSet<Integer> activeKeys;

    public InputHandler() {
        activeKeys = new HashSet<>();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        activeKeys.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        activeKeys.remove(e.getKeyCode());
    }

    public HashSet<Integer> getActiveKeys() {
        return activeKeys;
    }

}
