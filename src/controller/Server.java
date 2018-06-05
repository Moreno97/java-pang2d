package controller;

import gfx.SpriteSheetHandler;
import mapanel.Mapcanvas;
import sprites.Player;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Antonio Moreno Valls
 **/
public class Server extends Thread {

    private static final int PORT = 1024;
    private Stack<Player> playerStack;
    private String message;
    private int height;
    private Mapcanvas mapcanvas;

    private enum MoveTypes {
        DIRECTION_LEFT, DIRECTION_RIGHT
    }

    public Server(Stack<Player> playerStack, int height, Mapcanvas mapcanvas) {
        this.playerStack = playerStack;
        this.height = height;
        this.mapcanvas = mapcanvas;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public void run() {
        ServerSocket sSocket;
        try {
            sSocket = new ServerSocket(PORT);
            System.out.println("Server is ONLINE >> " + Inet4Address.getLocalHost().getHostAddress() + ":" + PORT);
            try {
                while (!sSocket.isClosed()) {
                    Thread t = new Thread(new Handler(sSocket.accept()));
                    t.start();
                }
            } finally {
                sSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private class Handler implements Runnable {

        private final Socket socket;
        private DataInputStream dataInputStream;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            System.out.println("\t" + "Client: " + socket.getInetAddress().getHostAddress());
            playerStack.add(new Player(350, height - 160, 50,
                    50, mapcanvas, 2, new ImageIcon(new SpriteSheetHandler("res/pj2.png").getImageWithoutCropping())));
            try {
                dataInputStream = new DataInputStream(socket.getInputStream());

                while (socket.isConnected()) {
                    String type = dataInputStream.readUTF();

                    if (type.equals("DIRECTION_LEFT")) {
                        playerStack.get(1).toLeft();
                    }

                    if (type.equals("DIRECTION_RIGHT")) {
                        playerStack.get(1).toRight();
                    }

                    if (type.equals("SHOOT")) {
                        mapcanvas.shootBullet(playerStack.get(1));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }
}
